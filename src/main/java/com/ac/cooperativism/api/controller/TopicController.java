package com.ac.cooperativism.api.controller;

import com.ac.cooperativism.api.model.TopicModel;
import com.ac.cooperativism.api.model.input.TopicInput;
import com.ac.cooperativism.domain.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/topics")
public class TopicController {

    private TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TopicModel create(@RequestBody @Valid TopicInput topicInput) {
        return topicService.create(topicInput);
    }
}
