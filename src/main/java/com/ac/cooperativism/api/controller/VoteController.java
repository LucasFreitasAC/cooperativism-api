package com.ac.cooperativism.api.controller;

import com.ac.cooperativism.api.model.CountVoteModel;
import com.ac.cooperativism.api.model.VoteModel;
import com.ac.cooperativism.api.model.input.VoteInput;
import com.ac.cooperativism.domain.service.VoteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/votes")
public class VoteController {

    private VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{document}")
    public VoteModel create(@PathVariable String document, @RequestBody @Valid VoteInput voteInput) {
        return voteService.create(document, voteInput);
    }

    @GetMapping("/topic/{topicId}")
    public CountVoteModel countVotes(@PathVariable Long topicId) {
        return voteService.countVotes(topicId);
    }
}
