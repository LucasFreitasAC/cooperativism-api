package com.ac.cooperativism.v1.domain.exception;

public class UserInvalidException extends BusinessException {

    private static final long serialVersionUID = 1L;

    public UserInvalidException(String mensagem) {
        super(mensagem);
    }

    public UserInvalidException(Long document) {
        this(String.format("Document %d is not available for voting", document));
    }
}
