package com.ac.cooperativism.api.model;

import lombok.*;

import javax.persistence.Column;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionModel {

    private Long id;

    @Column(columnDefinition = "datetime")
    private OffsetDateTime openDate;

    @Column(columnDefinition = "datetime")
    private OffsetDateTime closeDate;

    private TopicModel topic;
}
