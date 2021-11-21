package com.ac.cooperativism.api.assembler;

import com.ac.cooperativism.api.model.VoteModel;
import com.ac.cooperativism.domain.model.Vote;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VoteModelAssembler {

    private ModelMapper modelMapper;

    @Autowired
    public VoteModelAssembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public VoteModel toModel(Vote vote) {
        return modelMapper.map(vote, VoteModel.class);
    }

    public List<VoteModel> toCollectionModel(List<Vote> votes) {
        return votes.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
