package com.ac.cooperativism.domain.service;

import com.ac.cooperativism.api.model.TopicModel;
import com.ac.cooperativism.api.model.input.TopicInput;
import com.ac.cooperativism.domain.model.Topic;

public interface TopicService {

    public TopicModel create(TopicInput topicInput);

    public Topic searchOrFail(Long topicId);

}
