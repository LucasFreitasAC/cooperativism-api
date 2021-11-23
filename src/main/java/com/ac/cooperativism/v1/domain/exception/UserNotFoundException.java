package com.ac.cooperativism.v1.domain.exception;

public class UserNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public UserNotFoundException(String mensagem) {
        super(mensagem);
    }

    public UserNotFoundException(Long document) {
        this(String.format("User with document %d not found", document));
    }
}
