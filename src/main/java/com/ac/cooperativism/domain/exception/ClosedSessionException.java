package com.ac.cooperativism.domain.exception;

public class ClosedSessionException extends BusinessException {

    private static final long serialVersionUID = 1L;

    public ClosedSessionException(String mensagem) {
        super(mensagem);
    }

    public ClosedSessionException(Long topicId) {
        this(String.format("Voting session for code topic %d has ended", topicId));
    }
}
