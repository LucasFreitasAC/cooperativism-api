package com.ac.cooperativism.v1.domain.exception;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BusinessException(String mensagem) {
		super(mensagem);
	}

	public BusinessException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
