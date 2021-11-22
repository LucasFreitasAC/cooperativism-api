package com.ac.cooperativism.v1.api.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountVoteModel {

    private TopicModel topic;
    private Long resultYes;
    private Long resultNo;
}
