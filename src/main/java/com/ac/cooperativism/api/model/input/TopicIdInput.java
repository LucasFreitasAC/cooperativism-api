package com.ac.cooperativism.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TopicIdInput {

    @NotNull
    private Long id;
}
