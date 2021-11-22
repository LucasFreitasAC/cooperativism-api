package com.ac.cooperativism.api.controller;

import com.ac.cooperativism.api.model.CountVoteModel;
import com.ac.cooperativism.api.model.VoteModel;
import com.ac.cooperativism.api.model.input.VoteInput;
import com.ac.cooperativism.domain.service.VoteService;
import com.ac.cooperativism.exceptionhandler.Problem;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Votes")
@RestController
@RequestMapping("/votes")
public class VoteController {

    private VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @ApiOperation("Create a vote")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses({
            @ApiResponse(code = 201, message = "Vote registered"),
    })
    @PostMapping("/{document}")
    public VoteModel create(@ApiParam(value = "Membership Document", example = "123.345.876-32")
                            @PathVariable String document,
                            @ApiParam(name = "body", value = "Representation of a vote")
                            @RequestBody @Valid VoteInput voteInput) {
        return voteService.create(document, voteInput);
    }

    @ApiOperation("Retrieve vote count via topic ID")
    @GetMapping("/topic/{topicId}")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Vote ID invalid", response = Problem.class),
            @ApiResponse(code = 404, message = "Topic not found", response = Problem.class)
    })
    public CountVoteModel countVotes(@ApiParam(value = "Topic ID", example = "1")
                                     @PathVariable Long topicId) {
        return voteService.countVotes(topicId);
    }
}
