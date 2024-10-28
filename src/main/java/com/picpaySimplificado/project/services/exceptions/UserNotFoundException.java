package com.picpaySimplificado.project.services.exceptions;

public class UserNotFoundException extends RuntimeException{

	
	private static final long serialVersionUID = 1L;
	public UserNotFoundException(String mensage) {
		super(mensage);
	}
	
}
