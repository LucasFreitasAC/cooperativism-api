package com.ac.cooperativism.api.assembler;

import com.ac.cooperativism.api.model.TopicModel;
import com.ac.cooperativism.domain.model.Topic;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TopicModelAssembler {

    private ModelMapper modelMapper;

    @Autowired
    public TopicModelAssembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public TopicModel toModel(Topic topic) {
        return modelMapper.map(topic, TopicModel.class);
    }

    public List<TopicModel> toCollectionModel(List<Topic> topics) {
        return topics.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
