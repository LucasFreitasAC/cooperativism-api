package com.ac.cooperativism.v1.domain.exception;

public class UserNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public UserNotFoundException(String document) {
        super(String.format("User with document %s not found", document));
    }
}
