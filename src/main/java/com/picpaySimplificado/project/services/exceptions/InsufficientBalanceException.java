package com.picpaySimplificado.project.services.exceptions;

public class InsufficientBalanceException extends RuntimeException{


	private static final long serialVersionUID = 1L;
	public InsufficientBalanceException(String mensage) {
		super(mensage);
	}
}
