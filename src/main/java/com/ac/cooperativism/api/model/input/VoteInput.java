package com.ac.cooperativism.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class VoteInput {

    @Valid
    @NotNull
    private TopicIdInput topic;

    @NotNull
    private Boolean vote;
}
