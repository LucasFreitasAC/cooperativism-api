package com.ac.cooperativism.domain.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "topics")
public class Topics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @OneToOne(mappedBy = "topic")
    private Sessions session;

    @OneToOne(mappedBy = "topic")
    private Votes vote;
}
