package com.ac.cooperativism.v1.core.configuration;

import com.ac.cooperativism.v1.api.model.SendCountVoteModel;
import com.ac.cooperativism.v1.consumers.VoteProducer;
import com.ac.cooperativism.v1.domain.model.Session;
import com.ac.cooperativism.v1.domain.repository.VoteRepository;
import com.ac.cooperativism.v1.domain.service.SessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class ScheduleMessageCountVotes {

    private SessionService sessionService;

    private VoteProducer voteProducer;

    private VoteRepository voteRepository;

    @Autowired
    public ScheduleMessageCountVotes(SessionService sessionService, VoteProducer voteProducer, VoteRepository voteRepository) {
        this.sessionService = sessionService;
        this.voteProducer = voteProducer;
        this.voteRepository = voteRepository;
    }

    @Scheduled(cron = "0 * * * * *")
    public void sendMessageCloseDateEnded() {
        log.info("Start Sechduled");
        List<Session> sessions = sessionService.searchCloseDateEnded();
        sessions.forEach(session -> {
            Long voteNo = voteRepository.countByTopicAndVoteFalse(session.getTopic());
            Long voteYes = voteRepository.countByTopicAndVoteTrue(session.getTopic());

            SendCountVoteModel build = SendCountVoteModel.builder()
                    .topicId(session.getTopic().getId())
                    .voteNo(voteNo)
                    .voteYes(voteYes)
                    .build();

            voteProducer.sendMessage(build);
        });
        log.info("Close Sechduled");
    }
}
