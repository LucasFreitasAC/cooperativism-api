package com.ac.cooperativism.api.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CountVoteModel {

    private TopicModel topic;
    private Long resultYes;
    private Long resultNo;
}
