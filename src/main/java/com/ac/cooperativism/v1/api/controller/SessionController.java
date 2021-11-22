package com.ac.cooperativism.v1.api.controller;

import com.ac.cooperativism.v1.api.model.SessionModel;
import com.ac.cooperativism.v1.api.model.input.SessionInput;
import com.ac.cooperativism.v1.domain.service.SessionService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Sessions")
@RestController
@RequestMapping(path = "/v1/sessions", produces = MediaType.APPLICATION_JSON_VALUE)
public class SessionController {

    private SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @ApiOperation("Create a session")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses({
            @ApiResponse(code = 201, message = "Session registered"),
    })
    @PostMapping
    public SessionModel create(@ApiParam(name = "body", value = "Representation of a session")
                               @RequestBody @Valid SessionInput sessionInput) {
        return sessionService.create(sessionInput);
    }
}
