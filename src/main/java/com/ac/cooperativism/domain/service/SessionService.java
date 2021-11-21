package com.ac.cooperativism.domain.service;

import com.ac.cooperativism.api.model.SessionModel;
import com.ac.cooperativism.api.model.input.SessionInput;
import com.ac.cooperativism.domain.model.Session;

public interface SessionService {

    public SessionModel create(SessionInput sessionInput);

    public Session searchOrFail(Long sessionId);

}
