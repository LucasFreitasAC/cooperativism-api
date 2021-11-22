package com.ac.cooperativism.v1.api.assembler;

import com.ac.cooperativism.v1.api.model.input.VoteInput;
import com.ac.cooperativism.v1.domain.model.Vote;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VoteInputDisassembler {

    private ModelMapper modelMapper;

    @Autowired
    public VoteInputDisassembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Vote toDomainObject(VoteInput voteInput) {
        return modelMapper.map(voteInput, Vote.class);
    }

    public void copyToDomainObject(VoteInput voteInput, Vote vote) {
        modelMapper.map(voteInput, vote);
    }
}
