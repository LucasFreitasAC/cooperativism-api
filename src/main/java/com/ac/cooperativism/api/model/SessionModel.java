package com.ac.cooperativism.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.time.OffsetDateTime;

@Getter
@Setter
public class SessionModel {

    private Long id;

    @Column(columnDefinition = "datetime")
    private OffsetDateTime openDate;

    @Column(columnDefinition = "datetime")
    private OffsetDateTime closeDate;

    private TopicModel topic;
}
