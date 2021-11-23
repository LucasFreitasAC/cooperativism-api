package com.ac.cooperativism.v1.domain.service;

import com.ac.cooperativism.v1.api.model.CountVoteModel;
import com.ac.cooperativism.v1.api.model.VoteModel;
import com.ac.cooperativism.v1.api.model.input.VoteInput;

public interface VoteService {

    public VoteModel create(Long document, VoteInput voteInput);

    public CountVoteModel countVotes(Long topicId);

}
