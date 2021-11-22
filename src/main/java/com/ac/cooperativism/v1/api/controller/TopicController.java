package com.ac.cooperativism.v1.api.controller;

import com.ac.cooperativism.v1.api.model.TopicModel;
import com.ac.cooperativism.v1.api.model.input.TopicInput;
import com.ac.cooperativism.v1.domain.service.TopicService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Topics")
@RestController
@RequestMapping(path = "/v1/topics", produces = MediaType.APPLICATION_JSON_VALUE)
public class TopicController {

    private TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @ApiOperation("Create a topic")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses({
            @ApiResponse(code = 201, message = "Topic registered"),
    })
    @PostMapping
    public TopicModel create(@ApiParam(name = "body", value = "Representation of a topic")
                             @RequestBody @Valid TopicInput topicInput) {
        return topicService.create(topicInput);
    }
}
