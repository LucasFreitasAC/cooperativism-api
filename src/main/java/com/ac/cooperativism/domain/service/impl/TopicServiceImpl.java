package com.ac.cooperativism.domain.service.impl;

import com.ac.cooperativism.api.assembler.TopicInputDisassembler;
import com.ac.cooperativism.api.assembler.TopicModelAssembler;
import com.ac.cooperativism.api.model.TopicModel;
import com.ac.cooperativism.api.model.input.TopicInput;
import com.ac.cooperativism.domain.exception.TopicNotFoundException;
import com.ac.cooperativism.domain.model.Topic;
import com.ac.cooperativism.domain.repository.TopicRepository;
import com.ac.cooperativism.domain.service.TopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TopicServiceImpl implements TopicService {

    private TopicRepository topicRepository;

    private TopicInputDisassembler topicInputDisassembler;

    private TopicModelAssembler topicModelAssembler;

    @Autowired
    public TopicServiceImpl(TopicRepository topicRepository, TopicInputDisassembler topicInputDisassembler, TopicModelAssembler topicModelAssembler) {
        this.topicRepository = topicRepository;
        this.topicInputDisassembler = topicInputDisassembler;
        this.topicModelAssembler = topicModelAssembler;
    }

    public TopicModel create(TopicInput topicInput) {
        Topic topicToSave = topicInputDisassembler.toDomainObject(topicInput);
        Topic savedTopic = topicRepository.save(topicToSave);
        log.info("Topic created successfully, topic code is: {}", savedTopic.getId());
        return topicModelAssembler.toModel(savedTopic);
    }

    public Topic searchOrFail(Long topicId) {
        return topicRepository.findById(topicId).orElseThrow(() -> {
            log.error("There is no topic created with this code: {}", topicId);
            throw new TopicNotFoundException(topicId);
        });
    }
}
