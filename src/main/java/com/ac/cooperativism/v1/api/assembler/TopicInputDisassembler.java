package com.ac.cooperativism.v1.api.assembler;

import com.ac.cooperativism.v1.api.model.input.TopicInput;
import com.ac.cooperativism.v1.domain.model.Topic;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicInputDisassembler {

    private ModelMapper modelMapper;

    @Autowired
    public TopicInputDisassembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Topic toDomainObject(TopicInput topicInput) {
        return modelMapper.map(topicInput, Topic.class);
    }

    public void copyToDomainObject(TopicInput topicInput, Topic topic) {
        modelMapper.map(topicInput, topic);
    }
}
