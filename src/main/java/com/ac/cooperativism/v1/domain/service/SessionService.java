package com.ac.cooperativism.v1.domain.service;

import com.ac.cooperativism.v1.api.model.SessionModel;
import com.ac.cooperativism.v1.api.model.input.SessionInput;
import com.ac.cooperativism.v1.domain.model.Session;
import com.ac.cooperativism.v1.domain.model.Vote;

import java.util.List;

public interface SessionService {

    public SessionModel create(SessionInput sessionInput);

    public Session searchOrFail(Long sessionId);

    public List<Session> searchCloseDateEnded();

}
