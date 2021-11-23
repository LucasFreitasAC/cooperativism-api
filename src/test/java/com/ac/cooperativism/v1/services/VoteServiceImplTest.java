package com.ac.cooperativism.v1.services;

import com.ac.cooperativism.v1.api.assembler.VoteInputDisassembler;
import com.ac.cooperativism.v1.api.assembler.VoteModelAssembler;
import com.ac.cooperativism.v1.api.model.CountVoteModel;
import com.ac.cooperativism.v1.api.model.UserStatusModel;
import com.ac.cooperativism.v1.api.model.VoteModel;
import com.ac.cooperativism.v1.api.model.input.VoteInput;
import com.ac.cooperativism.v1.domain.exception.*;
import com.ac.cooperativism.v1.domain.model.Session;
import com.ac.cooperativism.v1.domain.model.Topic;
import com.ac.cooperativism.v1.domain.model.Vote;
import com.ac.cooperativism.v1.domain.repository.VoteRepository;
import com.ac.cooperativism.v1.domain.service.SessionService;
import com.ac.cooperativism.v1.domain.service.TopicService;
import com.ac.cooperativism.v1.domain.service.client.UserClientService;
import com.ac.cooperativism.v1.domain.service.impl.VoteServiceImpl;
import com.ac.cooperativism.v1.utils.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class VoteServiceImplTest {

    @InjectMocks
    private VoteServiceImpl voteService;

    @Mock
    private VoteRepository voteRepository;

    @Mock
    private VoteInputDisassembler voteInputDisassembler;

    @Mock
    private SessionService sessionService;

    @Mock
    private VoteModelAssembler voteModelAssembler;

    @Mock
    private TopicService topicService;

    @Mock
    private UserClientService userClientService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createVoteShouldReturnVoteModel() {
        //given
        String document = "12343281407";
        VoteInput voteInput = TestUtil.buildCreateVoteInput();
        Vote voteToSave = TestUtil.buildVoteToSave();
        Topic savedTopic = TestUtil.buildSavedTopicWithSession();
        Session savedSession = TestUtil.buildSavedSessionSendMessageFalse();
        Vote savedVote = TestUtil.buildSavedVote();
        VoteModel savedVoteModel = TestUtil.buildVoteModel();
        UserStatusModel userStatusModel = TestUtil.buildUserStatusModelAble();

        when(voteInputDisassembler.toDomainObject(voteInput)).thenReturn(voteToSave);
        when(voteRepository.findByTopicAndDocument(voteToSave.getTopic(), document)).thenReturn(null);
        when(userClientService.validateUserDocument(document)).thenReturn(userStatusModel);
        when(topicService.searchOrFail(voteToSave.getTopic().getId())).thenReturn(savedTopic);
        when(sessionService.searchOrFail(savedTopic.getSession().getId())).thenReturn(savedSession);
        when(voteRepository.save(voteToSave)).thenReturn(savedVote);
        when(voteModelAssembler.toModel(savedVote)).thenReturn(savedVoteModel);

        //when
        VoteModel voteModel = voteService.create(document, voteInput);

        //then
        assertNotEquals(null, voteModel);
        assertEquals(1L, voteModel.getId());
        assertEquals(true, voteModel.getVote());
        assertEquals(document, voteModel.getDocument());
        assertEquals(1L, voteModel.getTopic().getId());
        assertEquals("Topic for internet voting CLIG", voteModel.getTopic().getDescription());
    }

    @Test
    public void getAlreadyVotedExceptionWhenAssociateAlreadyVotedForTopic() {
        //given
        Long topicId = 1L;
        String document = "12343281407";
        VoteInput voteInput = TestUtil.buildCreateVoteInput();
        Vote voteToSave = TestUtil.buildVoteToSave();
        Vote savedVote = TestUtil.buildSavedVote();
        String expectedMessage = String.format("The associate with document %s has already voted for topic %d", document, topicId);

        when(voteInputDisassembler.toDomainObject(voteInput)).thenReturn(voteToSave);
        when(voteRepository.findByTopicAndDocument(voteToSave.getTopic(), document)).thenReturn(savedVote);

        //when
        AlreadyVotedException alreadyVotedException = assertThrows(AlreadyVotedException.class, () -> {
            voteService.create(document, voteInput);
        });

        //then
        String returnedMessage = alreadyVotedException.getMessage();

        assertEquals(expectedMessage, returnedMessage);
    }

    @Test
    public void getUserInvalidExceptionWhenAssociateUnableToVote() {
        //given
        String document = "12343281407";
        VoteInput voteInput = TestUtil.buildCreateVoteInput();
        Vote voteToSave = TestUtil.buildVoteToSave();
        UserStatusModel userStatusModel = TestUtil.buildUserStatusModelUnable();
        String expectedMessage = String.format("Document %s is not available for voting", document);

        when(voteInputDisassembler.toDomainObject(voteInput)).thenReturn(voteToSave);
        when(voteRepository.findByTopicAndDocument(voteToSave.getTopic(), document)).thenReturn(null);
        when(userClientService.validateUserDocument(document)).thenReturn(userStatusModel);

        //when
        UserInvalidException userInvalidException = assertThrows(UserInvalidException.class, () -> {
            voteService.create(document, voteInput);
        });

        //then
        String returnedMessage = userInvalidException.getMessage();

        assertEquals(expectedMessage, returnedMessage);
    }

    @Test
    public void getSessionNotOpenedExceptionWhenSessionNotOpenedForTopic() {
        //given
        Long topicId = 1L;
        String document = "12343281407";
        VoteInput voteInput = TestUtil.buildCreateVoteInput();
        Vote voteToSave = TestUtil.buildVoteToSave();
        Topic savedTopic = TestUtil.buildSavedTopicWithoutSession();
        UserStatusModel userStatusModel = TestUtil.buildUserStatusModelAble();
        String expectedMessage = String.format("There is no session open for this code topic %d.", topicId);

        when(voteInputDisassembler.toDomainObject(voteInput)).thenReturn(voteToSave);
        when(voteRepository.findByTopicAndDocument(voteToSave.getTopic(), document)).thenReturn(null);
        when(userClientService.validateUserDocument(document)).thenReturn(userStatusModel);
        when(topicService.searchOrFail(voteToSave.getTopic().getId())).thenReturn(savedTopic);

        //when
        SessionNotOpenedException sessionNotOpenedException = assertThrows(SessionNotOpenedException.class, () -> {
            voteService.create(document, voteInput);
        });

        //then
        String returnedMessage = sessionNotOpenedException.getMessage();

        assertEquals(expectedMessage, returnedMessage);
    }

    @Test
    public void getCloseDateEndedSessionExceptionWhenCloseDateEnded() {
        //given
        Long topicId = 1L;
        String document = "12343281407";
        VoteInput voteInput = TestUtil.buildCreateVoteInput();
        Vote voteToSave = TestUtil.buildVoteToSave();
        Topic savedTopic = TestUtil.buildSavedTopicWithSession();
        UserStatusModel userStatusModel = TestUtil.buildUserStatusModelAble();
        Session savedSession = TestUtil.buildSavedSessionWithWrongCloseDate();
        String expectedMessage = String.format("Voting session for code topic %d has ended", topicId);

        when(voteInputDisassembler.toDomainObject(voteInput)).thenReturn(voteToSave);
        when(voteRepository.findByTopicAndDocument(voteToSave.getTopic(), document)).thenReturn(null);
        when(userClientService.validateUserDocument(document)).thenReturn(userStatusModel);
        when(topicService.searchOrFail(voteToSave.getTopic().getId())).thenReturn(savedTopic);
        when(sessionService.searchOrFail(savedTopic.getSession().getId())).thenReturn(savedSession);

        //when
        CloseDateEndedSessionException closeDateEndedSessionException = assertThrows(CloseDateEndedSessionException.class, () -> {
            voteService.create(document, voteInput);
        });

        //then
        String returnedMessage = closeDateEndedSessionException.getMessage();

        assertEquals(expectedMessage, returnedMessage);
    }

    @Test
    public void countVotesShouldReturnCountVoteModel() {
        //given
        Long topicId = 1L;
        Topic savedTopic = TestUtil.buildSavedTopicWithSession();

        when(topicService.searchOrFail(topicId)).thenReturn(savedTopic);
        when(voteRepository.countByTopicAndVoteTrue(savedTopic)).thenReturn(10L);
        when(voteRepository.countByTopicAndVoteFalse(savedTopic)).thenReturn(5L);

        //when
        CountVoteModel countVoteModel = voteService.countVotes(topicId);

        assertNotEquals(null, countVoteModel);
        assertEquals(1L, countVoteModel.getTopic().getId());
        assertEquals("Topic for internet voting CLIG", countVoteModel.getTopic().getDescription());
        assertEquals(10L, countVoteModel.getResultYes());
        assertEquals(5L, countVoteModel.getResultNo());
    }

    @Test
    public void getTopicNotFoundExceptionWhenCreateVoteOrCountVotes() {
        //given
        Long topicId = 1L;
        String expectedMessage = String.format("There is no topic registration with the code %d", topicId);

        when(topicService.searchOrFail(topicId)).thenThrow(new TopicNotFoundException(topicId));

        //when
        TopicNotFoundException topicNotFoundException = assertThrows(TopicNotFoundException.class, () -> {
            topicService.searchOrFail(topicId);
        });

        //then
        String returnedMessage = topicNotFoundException.getMessage();

        assertEquals(expectedMessage, returnedMessage);
    }
}
