package com.picpaySimplificado.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.picpaySimplificado.project.dtos.TransactionDTO;
import com.picpaySimplificado.project.entites.Transaction;
import com.picpaySimplificado.project.services.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

	@Autowired
	private TransactionService service;
	
	@PostMapping
	public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO transaction) throws Exception{
		Transaction newTransaction = this.service.createTransaction(transaction);
		return new ResponseEntity<>(newTransaction, HttpStatus.OK);
	}
}
