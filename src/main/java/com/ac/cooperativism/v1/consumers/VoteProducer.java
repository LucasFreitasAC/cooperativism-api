package com.ac.cooperativism.v1.consumers;

import com.ac.cooperativism.v1.api.model.SendCountVoteModel;
import com.ac.cooperativism.v1.core.configuration.VoteConfiguration;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VoteProducer {

    private RabbitTemplate rabbitTemplate;

    private VoteConfiguration voteConfiguration;

    @Autowired
    public VoteProducer(RabbitTemplate rabbitTemplate, VoteConfiguration voteConfiguration) {
        this.rabbitTemplate = rabbitTemplate;
        this.voteConfiguration = voteConfiguration;
    }

    public void sendMessage(SendCountVoteModel sendCountVoteModel) {
        rabbitTemplate.convertAndSend(voteConfiguration.getExchange(), voteConfiguration.getRouting(), sendCountVoteModel);
    }
}
