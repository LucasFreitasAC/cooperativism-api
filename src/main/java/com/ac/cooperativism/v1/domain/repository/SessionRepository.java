package com.ac.cooperativism.v1.domain.repository;

import com.ac.cooperativism.v1.domain.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    List<Session> findBySendMessageFalseAndCloseDateLessThan(OffsetDateTime closeDate);

}
