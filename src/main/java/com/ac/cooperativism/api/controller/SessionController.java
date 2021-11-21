package com.ac.cooperativism.api.controller;

import com.ac.cooperativism.api.model.SessionModel;
import com.ac.cooperativism.api.model.input.SessionInput;
import com.ac.cooperativism.domain.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/sessions")
public class SessionController {

    private SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public SessionModel create(@RequestBody @Valid SessionInput sessionInput) {
        return sessionService.create(sessionInput);
    }
}
