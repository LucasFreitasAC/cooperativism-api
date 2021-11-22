package com.ac.cooperativism.v1.api.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TopicModel {

    private Long id;
    private String description;
}
