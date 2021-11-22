package com.ac.cooperativism.api.controller;

import com.ac.cooperativism.api.model.TopicModel;
import com.ac.cooperativism.api.model.input.TopicInput;
import com.ac.cooperativism.domain.service.TopicService;
import com.ac.cooperativism.exceptionhandler.Problem;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Topics")
@RestController
@RequestMapping("/topics")
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
