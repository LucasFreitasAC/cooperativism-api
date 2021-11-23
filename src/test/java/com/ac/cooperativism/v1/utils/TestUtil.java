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

    public static Topic buildCreateWrongTopic() {
        return Topic.builder()
                .description(null)
                .build();
    }

    public static Session buildCreateWrongSession() {
        return Session.builder()
                .topic(null)
                .build();
    }

    public static Vote buildCreateWrongVote() {
        return Vote.builder()
                .vote(null)
                .topic(null)
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
                .document("12343281407")
                .vote(true)
                .topic(Topic.builder()
                        .id(1L)
                        .build())
                .build();
    }

    public static Vote buildSavedVote() {
        return Vote.builder()
                .id(1L)
                .document("12343281407")
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
                .document("12343281407")
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
        return new ArrayList(List.of(buildSavedSessionSendMessageFalse()));
    }

    public static Iterable<Topic> populateTopicTable() {
        List<Topic> topics = new ArrayList<>();

        topics.add(Topic.builder()
                .description("Topic for internet voting CLIG")
                .build());

        topics.add(Topic.builder()
                .description("Topic to cover parking lot")
                .build());

        topics.add(Topic.builder()
                .description("Topic to put electric fence")
                .build());

        topics.add(Topic.builder()
                .description("Thread to allow animals to walk without being on lap")
                .build());

        return topics;
    }

    public static Iterable<Session> populateSessionTable() {
        List<Session> sessions = new ArrayList<>();

        sessions.add(Session.builder()
                .id(1L)
                .openDate(OffsetDateTime.now())
                .closeDate(OffsetDateTime.now().plusHours(1L))
                .topic(Topic.builder().id(1L).build())
                .sendMessage(Boolean.FALSE)
                .build());

        sessions.add(Session.builder()
                .id(2L)
                .openDate(OffsetDateTime.now())
                .closeDate(OffsetDateTime.now().plusHours(1L))
                .topic(Topic.builder().id(2L).build())
                .sendMessage(Boolean.FALSE)
                .build());

        sessions.add(Session.builder()
                .id(3L)
                .openDate(OffsetDateTime.now())
                .closeDate(OffsetDateTime.now().plusHours(1L))
                .topic(Topic.builder().id(3L).build())
                .sendMessage(Boolean.FALSE)
                .build());

        sessions.add(Session.builder()
                .id(4L)
                .openDate(OffsetDateTime.now())
                .closeDate(OffsetDateTime.now().plusHours(1L))
                .topic(Topic.builder().id(4L).build())
                .sendMessage(Boolean.FALSE)
                .build());

        return sessions;
    }

    public static Iterable<Vote> populateVoteTable() {
        List<Vote> votes = new ArrayList<>();

        votes.add(Vote.builder()
                .document("12343281407")
                .vote(true)
                .topic(Topic.builder()
                        .id(1L)
                        .build())
                .build());

        votes.add(Vote.builder()
                .document("12341321407")
                .vote(true)
                .topic(Topic.builder()
                        .id(1L)
                        .build())
                .build());

        votes.add(Vote.builder()
                .document("12343281176")
                .vote(false)
                .topic(Topic.builder()
                        .id(1L)
                        .build())
                .build());

        votes.add(Vote.builder()
                .document("52344281007")
                .vote(true)
                .topic(Topic.builder()
                        .id(1L)
                        .build())
                .build());

        return votes;
    }

}
