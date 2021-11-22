package com.ac.cooperativism.v1.api.model.input;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TopicInput {

    @NotBlank
    private String description;
}
