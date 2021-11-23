package com.ac.cooperativism.v1.api.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoteModel {

    private Long id;
    private TopicModel topic;
    private Long document;
    private Boolean vote;
}
