package com.ac.cooperativism.v1.domain.exception;

public class AlreadyVotedException extends BusinessException {

    private static final long serialVersionUID = 1L;

    public AlreadyVotedException(String mensagem) {
        super(mensagem);
    }

    public AlreadyVotedException(String document, Long topicId) {
        this(String.format("The associate with document %s has already voted for topic %d", document, topicId));
    }
}
