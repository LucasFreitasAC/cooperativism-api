package com.ac.cooperativism.v1.domain.exception;

public class TopicNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public TopicNotFoundException(String mensagem) {
        super(mensagem);
    }

    public TopicNotFoundException(Long topicId) {
        this(String.format("There is no topic registration with the code %d", topicId));
    }
}
