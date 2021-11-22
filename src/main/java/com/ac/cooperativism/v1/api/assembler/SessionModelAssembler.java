package com.ac.cooperativism.v1.api.assembler;

import com.ac.cooperativism.v1.api.model.SessionModel;
import com.ac.cooperativism.v1.domain.model.Session;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SessionModelAssembler {

    private ModelMapper modelMapper;

    @Autowired
    public SessionModelAssembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public SessionModel toModel(Session session) {
        return modelMapper.map(session, SessionModel.class);
    }

    public List<SessionModel> toCollectionModel(List<Session> sessions) {
        return sessions.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
