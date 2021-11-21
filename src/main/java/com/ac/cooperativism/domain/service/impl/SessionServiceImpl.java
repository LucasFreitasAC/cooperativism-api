package com.ac.cooperativism.domain.service.impl;

import com.ac.cooperativism.api.assembler.SessionInputDisassembler;
import com.ac.cooperativism.api.assembler.SessionModelAssembler;
import com.ac.cooperativism.api.model.SessionModel;
import com.ac.cooperativism.api.model.input.SessionInput;
import com.ac.cooperativism.domain.exception.BusinessException;
import com.ac.cooperativism.domain.exception.SessionNotFoundException;
import com.ac.cooperativism.domain.model.Session;
import com.ac.cooperativism.domain.model.Topic;
import com.ac.cooperativism.domain.repository.SessionRepository;
import com.ac.cooperativism.domain.service.SessionService;
import com.ac.cooperativism.domain.service.TopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

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
        sessionToSave.setCloseDate(sessionToSave.getCloseDate());

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

    public Session searchOrFail(Long sessionId) {
        return sessionRepository.findById(sessionId).orElseThrow(() -> {
            log.error("There is no session created with this code: {}", sessionId);
            throw new SessionNotFoundException(sessionId);
        });
    }
}
