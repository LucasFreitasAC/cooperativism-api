package com.ac.cooperativism.api.model.input;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoteInput {

    @Valid
    @NotNull
    private TopicIdInput topic;

    @NotNull
    private Boolean vote;
}
