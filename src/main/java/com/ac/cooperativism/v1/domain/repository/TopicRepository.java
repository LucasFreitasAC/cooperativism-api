package com.ac.cooperativism.v1.domain.repository;

import com.ac.cooperativism.v1.domain.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
}
