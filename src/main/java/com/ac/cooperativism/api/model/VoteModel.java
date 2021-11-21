package com.ac.cooperativism.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoteModel {

    private Long id;
    private TopicModel topic;
    private String document;
    private Boolean vote;
}
