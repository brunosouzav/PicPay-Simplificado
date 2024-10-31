package com.picpaySimplificado.project.infra;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.picpaySimplificado.project.dtos.ExceptionDTO;

@RestControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity threatDuplicateEntry(DataIntegrityViolationException exception) {
		ExceptionDTO exceptionDTO = new ExceptionDTO("Already registered user", "400");
		return ResponseEntity.badRequest().body(exceptionDTO);
		
	}
}
