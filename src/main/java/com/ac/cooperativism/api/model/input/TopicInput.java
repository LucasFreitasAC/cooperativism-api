package com.ac.cooperativism.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class TopicInput {

    @NotBlank
    private String description;
}
