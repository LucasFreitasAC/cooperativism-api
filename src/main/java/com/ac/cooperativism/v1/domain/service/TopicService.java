package com.ac.cooperativism.v1.domain.service;

import com.ac.cooperativism.v1.api.model.TopicModel;
import com.ac.cooperativism.v1.api.model.input.TopicInput;
import com.ac.cooperativism.v1.domain.model.Topic;

public interface TopicService {

    public TopicModel create(TopicInput topicInput);

    public Topic searchOrFail(Long topicId);

}
