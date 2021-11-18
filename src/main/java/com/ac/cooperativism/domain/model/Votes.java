package com.ac.cooperativism.domain.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "vote")
public class Votes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "topic_id", referencedColumnName = "id")
    private Topics topic;

    private String document;

    private Boolean vote;
}
