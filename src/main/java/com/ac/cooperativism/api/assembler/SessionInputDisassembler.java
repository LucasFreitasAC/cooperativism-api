package com.ac.cooperativism.api.assembler;

import com.ac.cooperativism.api.model.input.SessionInput;
import com.ac.cooperativism.domain.model.Session;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessionInputDisassembler {

    private ModelMapper modelMapper;

    @Autowired
    public SessionInputDisassembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Session toDomainObject(SessionInput sessionInput) {
        return modelMapper.map(sessionInput, Session.class);
    }

    public void copyToDomainObject(SessionInput sessionInput, Session session) {
        modelMapper.map(sessionInput, session);
    }
}
