package com.ac.cooperativism.domain.service;

import com.ac.cooperativism.api.model.CountVoteModel;
import com.ac.cooperativism.api.model.VoteModel;
import com.ac.cooperativism.api.model.input.VoteInput;

public interface VoteService {

    public VoteModel create(String document, VoteInput voteInput);

    public CountVoteModel countVotes(Long topicId);

}
