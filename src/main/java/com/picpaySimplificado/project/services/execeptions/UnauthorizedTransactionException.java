package com.picpaySimplificado.project.services.execeptions;

public class UnauthorizedTransactionException extends RuntimeException {
  
	private static final long serialVersionUID = 1L;

	public UnauthorizedTransactionException(String message) {
        super(message);
    }
}