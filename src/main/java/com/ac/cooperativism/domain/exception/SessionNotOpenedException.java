package com.ac.cooperativism.domain.exception;

public class SessionNotOpenedException extends BusinessException {

    private static final long serialVersionUID = 1L;

    public SessionNotOpenedException(String mensagem) {
        super(mensagem);
    }

    public SessionNotOpenedException(Long topicId) {
        this(String.format("There is no session open for this code topic %d.", topicId));
    }
}
