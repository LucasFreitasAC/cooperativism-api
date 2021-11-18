package com.ac.cooperativism.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "sessions")
public class Sessions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime openDate;

    private LocalDateTime closeDate;

    @OneToOne
    @JoinColumn(name = "topic_id", referencedColumnName = "id")
    private Topics topic;
}
