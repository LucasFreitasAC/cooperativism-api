package com.ac.cooperativism.v1.services;

import com.ac.cooperativism.v1.utils.TestUtil;
import com.ac.cooperativism.v1.api.assembler.TopicInputDisassembler;
import com.ac.cooperativism.v1.api.assembler.TopicModelAssembler;
import com.ac.cooperativism.v1.api.model.TopicModel;
import com.ac.cooperativism.v1.api.model.input.TopicInput;
import com.ac.cooperativism.v1.domain.exception.TopicNotFoundException;
import com.ac.cooperativism.v1.domain.model.Topic;
import com.ac.cooperativism.v1.domain.repository.TopicRepository;
import com.ac.cooperativism.v1.domain.service.impl.TopicServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class TopicServiceImplTest {

    @InjectMocks
    private TopicServiceImpl topicService;

    @Mock
    private TopicRepository topicRepository;

    @Mock
    private TopicInputDisassembler topicInputDisassembler;

    @Mock
    private TopicModelAssembler topicModelAssembler;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createTopicShouldReturnTopicModel() {
        //given
        TopicInput topicInput = TestUtil.buildCreateTopicInput();
        Topic topicToSave = TestUtil.buildCreateTopic();
        Topic savedTopic = TestUtil.buildTopic();
        TopicModel savedTopicModel = TestUtil.buildTopicModel();

        when(topicInputDisassembler.toDomainObject(topicInput)).thenReturn(topicToSave);
        when(topicRepository.save(topicToSave)).thenReturn(savedTopic);
        when(topicModelAssembler.toModel(savedTopic)).thenReturn(savedTopicModel);

        //when
        TopicModel topicModel = topicService.create(topicInput);

        //then
        assertNotEquals(null, topicModel);
        assertEquals(1L, topicModel.getId());
        assertEquals("Topic for internet voting CLIG", topicModel.getDescription());
    }

    @Test
    public void getTopicNotFoundExceptionWhenNotFoundTopic() {
        //given
        Long topicId = 1L;
        String expectedMessage = String.format("There is no topic registration with the code %d", topicId);

        when(topicRepository.findById(topicId)).thenThrow(new TopicNotFoundException(topicId));

        //when
        TopicNotFoundException topicNotFoundException = assertThrows(TopicNotFoundException.class, () -> {
            topicService.searchOrFail(topicId);
        });

        //then
        String returnedMessage = topicNotFoundException.getMessage();

        assertEquals(expectedMessage, returnedMessage);
    }
}
