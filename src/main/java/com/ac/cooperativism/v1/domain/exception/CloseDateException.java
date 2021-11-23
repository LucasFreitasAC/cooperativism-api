package com.ac.cooperativism.v1.domain.exception;

public class CloseDateException extends BusinessException {

    private static final long serialVersionUID = 1L;

    public CloseDateException(String mensagem) {
        super(mensagem);
    }

    public CloseDateException() {
        this("The date entered is less than the current date");
    }
}
