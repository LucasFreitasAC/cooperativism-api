package com.ac.cooperativism.v1.services;

import com.ac.cooperativism.v1.utils.TestUtil;
import com.ac.cooperativism.v1.api.assembler.SessionInputDisassembler;
import com.ac.cooperativism.v1.api.assembler.SessionModelAssembler;
import com.ac.cooperativism.v1.api.model.SessionModel;
import com.ac.cooperativism.v1.api.model.input.SessionInput;
import com.ac.cooperativism.v1.domain.exception.BusinessException;
import com.ac.cooperativism.v1.domain.exception.CloseDateException;
import com.ac.cooperativism.v1.domain.exception.SessionNotFoundException;
import com.ac.cooperativism.v1.domain.model.Session;
import com.ac.cooperativism.v1.domain.model.Topic;
import com.ac.cooperativism.v1.domain.repository.SessionRepository;
import com.ac.cooperativism.v1.domain.service.impl.SessionServiceImpl;
import com.ac.cooperativism.v1.domain.service.impl.TopicServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class SessionServiceImplTest {

    @InjectMocks
    private SessionServiceImpl sessionService;

    @Mock
    private TopicServiceImpl topicService;

    @Mock
    private SessionInputDisassembler sessionInputDisassembler;

    @Mock
    private SessionModelAssembler sessionModelAssembler;

    @Mock
    private SessionRepository sessionRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createSessionWithNullCloseDateShouldReturnSessionModel() {
        //given
        Long topicId = 1L;
        SessionInput sessionInput = TestUtil.buildSessionInputWithCloseDataNull();
        Topic topic = TestUtil.buildTopic();
        Session session = TestUtil.buildSession();
        Session savedSession = TestUtil.buildSavedSessionSendMessageFalse();
        SessionModel savedSessionModel = TestUtil.buildSessionModel();

        when(sessionInputDisassembler.toDomainObject(sessionInput)).thenReturn(session);
        when(topicService.searchOrFail(topicId)).thenReturn(topic);
        when(sessionRepository.save(session)).thenReturn(savedSession);
        when(sessionModelAssembler.toModel(savedSession)).thenReturn(savedSessionModel);

        //when
        SessionModel sessionModel = sessionService.create(sessionInput);

        //then
        assertEquals(1L, sessionModel.getId());
        assertNotEquals(null, sessionModel.getCloseDate());
    }

    @Test
    public void createSessionWithValueCloseDateShouldReturnSessionModel() {
        //given
        Long topicId = 1L;
        SessionInput sessionInput = TestUtil.buildSessionInput();
        Topic topic = TestUtil.buildTopic();
        Session session = TestUtil.buildSession();
        Session savedSession = TestUtil.buildSavedSessionSendMessageFalse();
        SessionModel savedSessionModel = TestUtil.buildSessionModel();

        when(sessionInputDisassembler.toDomainObject(sessionInput)).thenReturn(session);
        when(topicService.searchOrFail(topicId)).thenReturn(topic);
        when(sessionRepository.save(session)).thenReturn(savedSession);
        when(sessionModelAssembler.toModel(savedSession)).thenReturn(savedSessionModel);

        //when
        SessionModel sessionModel = sessionService.create(sessionInput);

        //then
        assertEquals(1L, sessionModel.getId());
        assertNotEquals(null, sessionModel.getCloseDate());
    }

    @Test
    public void getCloseDateExceptionWhenCloseDateLessThanCurrentDate() {
        //given
        SessionInput sessionInput = TestUtil.buildSessionInput();
        Session session = TestUtil.buildSavedSessionWithWrongCloseDate();
        String expectedMessage = "The date entered is less than the current date";

        when(sessionInputDisassembler.toDomainObject(sessionInput)).thenReturn(session);

        //when
        CloseDateException closeDateException = assertThrows(CloseDateException.class, () -> {
            sessionService.create(sessionInput);
        });

        //then
        String returnedMessage = closeDateException.getMessage();

        assertEquals(expectedMessage, returnedMessage);
    }

    @Test
    public void getBusinessExceptionWhenAlreadyExistsSessionWithTopic() {
        //given
        Long topicId = 1L;
        SessionInput sessionInput = TestUtil.buildSessionInput();
        Topic topic = TestUtil.buildTopic();
        Session session = TestUtil.buildSession();
        String expectedMessage = "It is only possible to create one session per topic.";

        when(sessionInputDisassembler.toDomainObject(sessionInput)).thenReturn(session);
        when(topicService.searchOrFail(topicId)).thenReturn(topic);
        when(sessionRepository.save(session)).thenThrow(DataIntegrityViolationException.class);

        //when
        BusinessException businessException = assertThrows(BusinessException.class, () -> {
            sessionService.create(sessionInput);
        });

        //then
        String returnedMessage = businessException.getMessage();

        assertEquals(expectedMessage, returnedMessage);
    }

    @Test
    public void searchCloseDateEnded() {
        //given
        List<Session> sessions = TestUtil.buildSessionListCloseDateEnded();
        Session savedSessionSendMessageFalse = TestUtil.buildSavedSessionSendMessageFalse();
        Session savedSessionSendMessageTrue = TestUtil.buildSavedSessionSendMessageTrue();

        when(sessionRepository.findBySendMessageFalseAndCloseDateLessThan(any())).thenReturn(sessions);
        when(sessionRepository.save(savedSessionSendMessageFalse)).thenReturn(savedSessionSendMessageTrue);

        //when
        List<Session> result = sessionService.searchCloseDateEnded();

        //then
        assertEquals(sessions.size(), result.size());
        assertEquals(sessions.get(0).getId(), result.get(0).getId());
    }

    @Test
    public void getSessionsWithSessionId() {
        //given
        Long sessionId = 1L;
        Optional<Session> session = TestUtil.buildOptionalSession();

        when(sessionRepository.findById(sessionId)).thenReturn(session);

        //when
        Session savedSession = sessionService.searchOrFail(sessionId);

        //then
        assertNotEquals(null, savedSession);
        assertEquals(1L, savedSession.getId());
    }

    @Test
    public void getSessionNotFoundExceptionWhenNotFoundSession() {
        //given
        Long sessionId = 1L;
        String expectedMessage = String.format("There is no session registration with the code %d", sessionId);

        when(sessionRepository.findById(sessionId)).thenThrow(new SessionNotFoundException(sessionId));

        //when
        SessionNotFoundException sessionNotFoundException = assertThrows(SessionNotFoundException.class, () -> {
            sessionService.searchOrFail(sessionId);
        });

        //then
        String returnedMessage = sessionNotFoundException.getMessage();

        assertEquals(expectedMessage, returnedMessage);
    }
}
