package com.ac.cooperativism.v1.domain.exception;

public class CloseDateEndedSessionException extends BusinessException {

    private static final long serialVersionUID = 1L;

    public CloseDateEndedSessionException(String mensagem) {
        super(mensagem);
    }

    public CloseDateEndedSessionException(Long topicId) {
        this(String.format("Voting session for code topic %d has ended", topicId));
    }
}
