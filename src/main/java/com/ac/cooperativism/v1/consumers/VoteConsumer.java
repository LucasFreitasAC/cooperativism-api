package com.ac.cooperativism.v1.consumers;

import com.ac.cooperativism.v1.api.model.SendCountVoteModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class VoteConsumer {

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void messageReceived(@Payload SendCountVoteModel sendCountVoteModel) {
        log.info(String.format("The code topic %d was closed, had %d votes in favor and %d votes against", sendCountVoteModel.getTopicId(), sendCountVoteModel.getVoteYes(), sendCountVoteModel.getVoteNo()));
    }

}
