package com.ac.cooperativism.utils;

import com.ac.cooperativism.api.model.SessionModel;
import com.ac.cooperativism.api.model.TopicModel;
import com.ac.cooperativism.api.model.input.SessionInput;
import com.ac.cooperativism.api.model.input.TopicIdInput;
import com.ac.cooperativism.domain.model.Session;
import com.ac.cooperativism.domain.model.Topic;

import java.time.OffsetDateTime;
import java.util.Optional;

public class TestUtils {

    public static SessionInput buildSessionInputWithCloseDataNull() {
        return SessionInput.builder()
                .closeDate(null)
                .topic(TopicIdInput.builder()
                        .id(1L)
                        .build())
                .build();
    }

    public static SessionInput buildSessionInput() {
        return SessionInput.builder()
                .closeDate(OffsetDateTime.now())
                .topic(TopicIdInput.builder()
                        .id(1L)
                        .build())
                .build();
    }

    public static Session buildSavedSession() {
        return Session.builder()
                .id(1L)
                .openDate(OffsetDateTime.now())
                .closeDate(OffsetDateTime.now().plusMinutes(1))
                .topic(buildTopic())
                .build();
    }

    public static Session buildSession() {
        return Session.builder()
                .openDate(OffsetDateTime.now())
                .closeDate(OffsetDateTime.now().plusMinutes(1))
                .topic(buildTopic())
                .build();
    }

    public static Optional<Session> buildOptionalSession() {
        return Optional.ofNullable(buildSavedSession());
    }

    public static Topic buildTopic() {
        return Topic.builder()
                .id(1L)
                .description("Topic for internet voting CLIG")
                .build();
    }

    public static SessionModel buildSessionModel() {
        return SessionModel.builder()
                .id(1L)
                .closeDate(OffsetDateTime.now().plusMinutes(1))
                .topic(buildTopicModel())
                .build();
    }

    public static TopicModel buildTopicModel() {
        return TopicModel.builder()
                .id(1L)
                .description("Topic for internet voting CLIG")
                .build();
    }

}
