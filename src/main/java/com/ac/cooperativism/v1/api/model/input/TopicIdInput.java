package com.ac.cooperativism.v1.api.model.input;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TopicIdInput {

    @NotNull
    private Long id;
}
