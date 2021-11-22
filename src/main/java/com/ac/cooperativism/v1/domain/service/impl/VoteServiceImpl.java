package com.ac.cooperativism.v1.domain.service.impl;

import com.ac.cooperativism.v1.api.assembler.VoteInputDisassembler;
import com.ac.cooperativism.v1.api.assembler.VoteModelAssembler;
import com.ac.cooperativism.v1.api.model.CountVoteModel;
import com.ac.cooperativism.v1.api.model.TopicModel;
import com.ac.cooperativism.v1.api.model.VoteModel;
import com.ac.cooperativism.v1.api.model.input.VoteInput;
import com.ac.cooperativism.v1.domain.exception.AlreadyVotedException;
import com.ac.cooperativism.v1.domain.exception.CloseDateEndedSessionException;
import com.ac.cooperativism.v1.domain.exception.SessionNotOpenedException;
import com.ac.cooperativism.v1.domain.model.Session;
import com.ac.cooperativism.v1.domain.model.Topic;
import com.ac.cooperativism.v1.domain.model.Vote;
import com.ac.cooperativism.v1.domain.repository.VoteRepository;
import com.ac.cooperativism.v1.domain.service.SessionService;
import com.ac.cooperativism.v1.domain.service.TopicService;
import com.ac.cooperativism.v1.domain.service.VoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Slf4j
@Service
public class VoteServiceImpl implements VoteService {

    private VoteRepository voteRepository;

    private VoteInputDisassembler voteInputDisassembler;

    private SessionService sessionService;

    private VoteModelAssembler voteModelAssembler;

    private TopicService topicService;

    @Autowired
    public VoteServiceImpl(VoteRepository voteRepository, VoteInputDisassembler voteInputDisassembler, SessionService sessionService, VoteModelAssembler voteModelAssembler, TopicService topicService) {
        this.voteRepository = voteRepository;
        this.voteInputDisassembler = voteInputDisassembler;
        this.sessionService = sessionService;
        this.voteModelAssembler = voteModelAssembler;
        this.topicService = topicService;
    }

    public VoteModel create(String document, VoteInput voteInput) {
        Vote voteToSave = voteInputDisassembler.toDomainObject(voteInput);
        Vote alreadyVoted = voteRepository.findByTopicAndDocument(voteToSave.getTopic(), document);

        if (alreadyVoted != null) {
            log.error("The associate with document {} has already voted for topic {}", document, voteToSave.getTopic().getId());
            throw new AlreadyVotedException(document, voteToSave.getTopic().getId());
        }
        Topic savedTopic = topicService.searchOrFail(voteToSave.getTopic().getId());

        if (savedTopic.getSession() == null) {
            log.error("There is no session open for this code topic {}.", voteToSave.getTopic().getId());
            throw new SessionNotOpenedException(savedTopic.getId());
        }
        Session savedSession = sessionService.searchOrFail(savedTopic.getSession().getId());

        if (savedSession.getCloseDate().isAfter(OffsetDateTime.now())) {
            voteToSave.setTopic(savedTopic);
            voteToSave.setDocument(document);
            VoteModel savedVote = voteModelAssembler.toModel(voteRepository.save(voteToSave));
            log.info("Vote created successfully, vote code is: {}", savedVote.getId());
            return savedVote;
        }
        log.error("Voting session for code topic {} has ended", voteToSave.getTopic().getId());
        throw new CloseDateEndedSessionException(voteToSave.getTopic().getId());
    }

    public CountVoteModel countVotes(Long topicId) {
        Topic savedTopic = topicService.searchOrFail(topicId);
        return CountVoteModel.builder()
                .topic(TopicModel.builder()
                        .id(savedTopic.getId())
                        .description(savedTopic.getDescription())
                        .build())
                .resultYes(voteRepository.countByTopicAndVoteTrue(savedTopic))
                .resultNo(voteRepository.countByTopicAndVoteFalse(savedTopic))
                .build();
    }
}
