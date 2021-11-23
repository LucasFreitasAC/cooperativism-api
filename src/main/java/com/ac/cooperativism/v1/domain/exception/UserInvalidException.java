package com.ac.cooperativism.v1.domain.exception;

public class UserInvalidException extends BusinessException {

    private static final long serialVersionUID = 1L;

    public UserInvalidException(String document) {
        super(String.format("Document %s is not available for voting", document));
    }
}
