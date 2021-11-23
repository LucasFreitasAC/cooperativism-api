package com.ac.cooperativism.v1.consumers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class VoteConsumer {

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void messageReceived(@Payload String body) {
        log.info("Consumer message: " + body);
    }

}
