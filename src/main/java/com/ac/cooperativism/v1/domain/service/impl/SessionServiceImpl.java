package com.ac.cooperativism.v1.domain.service.impl;

import com.ac.cooperativism.v1.api.assembler.SessionInputDisassembler;
import com.ac.cooperativism.v1.api.assembler.SessionModelAssembler;
import com.ac.cooperativism.v1.api.model.SessionModel;
import com.ac.cooperativism.v1.api.model.input.SessionInput;
import com.ac.cooperativism.v1.domain.exception.BusinessException;
import com.ac.cooperativism.v1.domain.exception.CloseDateException;
import com.ac.cooperativism.v1.domain.exception.SessionNotFoundException;
import com.ac.cooperativism.v1.domain.model.Session;
import com.ac.cooperativism.v1.domain.model.Topic;
import com.ac.cooperativism.v1.domain.model.Vote;
import com.ac.cooperativism.v1.domain.repository.SessionRepository;
import com.ac.cooperativism.v1.domain.repository.VoteRepository;
import com.ac.cooperativism.v1.domain.service.SessionService;
import com.ac.cooperativism.v1.domain.service.TopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SessionServiceImpl implements SessionService {

    private SessionRepository sessionRepository;

    private SessionInputDisassembler sessionInputDisassembler;

    private SessionModelAssembler sessionModelAssembler;

    private TopicService topicService;

    @Autowired
    public SessionServiceImpl(SessionRepository sessionRepository, SessionInputDisassembler sessionInputDisassembler, SessionModelAssembler sessionModelAssembler, TopicService topicService) {
        this.sessionRepository = sessionRepository;
        this.sessionInputDisassembler = sessionInputDisassembler;
        this.sessionModelAssembler = sessionModelAssembler;
        this.topicService = topicService;
    }

    public SessionModel create(SessionInput sessionInput) {
        Session sessionToSave = sessionInputDisassembler.toDomainObject(sessionInput);

        if (sessionToSave.getOpenDate().isAfter(sessionToSave.getCloseDate())) {
            throw new CloseDateException();
        }

        Long topicId = sessionToSave.getTopic().getId();
        Topic topic = topicService.searchOrFail(topicId);

        sessionToSave.setTopic(topic);
        try {
            SessionModel savedSession = sessionModelAssembler.toModel(sessionRepository.save(sessionToSave));
            log.info("Session created successfully, session code is: {}", savedSession.getId());
            return savedSession;
        } catch (DataIntegrityViolationException ex) {
            log.error("It is only possible to create one session per topic.");
            throw new BusinessException("It is only possible to create one session per topic.");
        }
    }

    public List<Session> searchCloseDateEnded() {
        List<Session> openDateSession = sessionRepository.findBySendMessageFalseAndCloseDateLessThan(OffsetDateTime.now());
        return openDateSession.stream().map(this::updateSessionAndReturnVote).collect(Collectors.toList());
    }

    private Session updateSessionAndReturnVote(Session session) {
        session.setSendMessage(Boolean.TRUE);
        return sessionRepository.save(session);
    }

    public Session searchOrFail(Long sessionId) {
        return sessionRepository.findById(sessionId).orElseThrow(() -> {
            log.error("There is no session created with this code: {}", sessionId);
            throw new SessionNotFoundException(sessionId);
        });
    }
}
