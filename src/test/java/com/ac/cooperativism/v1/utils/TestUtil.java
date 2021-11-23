package com.ac.cooperativism.v1.utils;

import com.ac.cooperativism.v1.api.model.SessionModel;
import com.ac.cooperativism.v1.api.model.TopicModel;
import com.ac.cooperativism.v1.api.model.UserStatusModel;
import com.ac.cooperativism.v1.api.model.VoteModel;
import com.ac.cooperativism.v1.api.model.input.SessionInput;
import com.ac.cooperativism.v1.api.model.input.TopicIdInput;
import com.ac.cooperativism.v1.api.model.input.TopicInput;
import com.ac.cooperativism.v1.api.model.input.VoteInput;
import com.ac.cooperativism.v1.domain.model.Session;
import com.ac.cooperativism.v1.domain.model.Topic;
import com.ac.cooperativism.v1.domain.model.Vote;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestUtil {

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

    public static Session buildSavedSessionSendMessageFalse() {
        return Session.builder()
                .id(1L)
                .openDate(OffsetDateTime.now())
                .closeDate(OffsetDateTime.now().plusMinutes(1))
                .topic(buildTopic())
                .sendMessage(Boolean.FALSE)
                .build();
    }

    public static Session buildSavedSessionSendMessageTrue() {
        return Session.builder()
                .id(1L)
                .openDate(OffsetDateTime.now())
                .closeDate(OffsetDateTime.now().plusMinutes(1))
                .topic(buildTopic())
                .sendMessage(Boolean.TRUE)
                .build();
    }

    public static Session buildSavedSessionWithWrongCloseDate() {
        return Session.builder()
                .id(1L)
                .openDate(OffsetDateTime.now().minusMinutes(10))
                .closeDate(OffsetDateTime.now().minusMinutes(5))
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
        return Optional.ofNullable(buildSavedSessionSendMessageFalse());
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

    public static TopicInput buildCreateTopicInput() {
        return TopicInput.builder()
                .description("Topic for internet voting CLIG")
                .build();
    }

    public static Topic buildCreateTopic() {
        return Topic.builder()
                .description("Topic for internet voting CLIG")
                .build();
    }

    public static VoteInput buildCreateVoteInput() {
        return VoteInput.builder()
                .vote(true)
                .topic(TopicIdInput.builder()
                        .id(1L)
                        .build())
                .build();
    }

    public static Vote buildVoteToSave() {
        return Vote.builder()
                .document(12343281407L)
                .vote(true)
                .topic(Topic.builder()
                        .id(1L)
                        .build())
                .build();
    }

    public static Vote buildSavedVote() {
        return Vote.builder()
                .id(1L)
                .document(12343281407L)
                .vote(true)
                .topic(Topic.builder()
                        .id(1L)
                        .build())
                .build();
    }

    public static Topic buildSavedTopicWithSession() {
        return Topic.builder()
                .id(1L)
                .description("Topic for internet voting CLIG")
                .session(Session.builder()
                        .id(1L)
                        .build())
                .build();
    }

    public static Topic buildSavedTopicWithoutSession() {
        return Topic.builder()
                .id(1L)
                .description("Topic for internet voting CLIG")
                .build();
    }

    public static VoteModel buildVoteModel() {
        return VoteModel.builder()
                .id(1L)
                .document(12343281407L)
                .vote(true)
                .topic(buildTopicModel())
                .build();
    }

    public static UserStatusModel buildUserStatusModelAble() {
        return UserStatusModel.builder()
                .status("ABLE_TO_VOTE")
                .build();
    }

    public static UserStatusModel buildUserStatusModelUnable() {
        return UserStatusModel.builder()
                .status("UNABLE_TO_VOTE")
                .build();
    }

    public static List<Session> buildSessionListCloseDateEnded() {
//        Session session = Session.builder()
//                .id(1L)
//                .openDate(OffsetDateTime.now())
//                .closeDate(OffsetDateTime.now().plusMinutes(1))
//                .topic(buildTopic())
//                .sendMessage(Boolean.TRUE)
//                .build();

        return new ArrayList(List.of(buildSavedSessionSendMessageFalse()));
    }

}