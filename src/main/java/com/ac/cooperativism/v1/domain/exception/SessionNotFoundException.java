package com.ac.cooperativism.v1.domain.exception;

public class SessionNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public SessionNotFoundException(String mensagem) {
        super(mensagem);
    }

    public SessionNotFoundException(Long sessionId) {
        this(String.format("There is no session registration with the code %d", sessionId));
    }
}
