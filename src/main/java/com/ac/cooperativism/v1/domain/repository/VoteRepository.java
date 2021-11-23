package com.ac.cooperativism.v1.domain.repository;

import com.ac.cooperativism.v1.domain.model.Topic;
import com.ac.cooperativism.v1.domain.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    Long countByTopicAndVoteTrue(Topic topic);

    Long countByTopicAndVoteFalse(Topic topic);

    Vote findByTopicAndDocument(Topic topic, Long document);

    Vote findByTopic(Topic topic);

}
