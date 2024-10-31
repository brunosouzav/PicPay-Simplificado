package com.picpaySimplificado.project.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.picpaySimplificado.project.dtos.TransactionDTO;
import com.picpaySimplificado.project.entites.Transaction;
import com.picpaySimplificado.project.entites.User;
import com.picpaySimplificado.project.repositories.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private UserService service;
	
	@Autowired
	private TransactionRepository repository;
	
	@Autowired
	private RestTemplate restTemplate; 
	
	@Autowired
	private NotificationService notification;
	
	public Transaction createTransaction (TransactionDTO transaction) throws Exception {
		
		User sender = service.findById(transaction.senderId());
		User receiver = service.findById(transaction.receiverId());
		
		service.validateTransaction(sender, transaction.value());
	
		Transaction newTransaction = new Transaction();
		newTransaction.setAmount(transaction.value());
		newTransaction.setSender(sender);
		newTransaction.setReceiver(receiver);
		newTransaction.setTimesTemp(LocalDateTime.now());
		
		sender.setBalance(sender.getBalance().subtract(transaction.value()));
		receiver.setBalance(receiver.getBalance().add(transaction.value()));
		
		this.repository.save(newTransaction);
		this.service.saveUser(sender);
		this.service.saveUser(receiver);
		
		notification.sendNotification(sender, "Transação realizada com sucesso");
		notification.sendNotification(receiver, "Transação recebida ");
		
		return newTransaction;
	}

	public boolean authorizeTransaction(User sender, BigDecimal value) {
		ResponseEntity<Map> authorization = restTemplate.getForEntity
				("https://util.devi.tools/api/v2/authorize", Map.class);
		if (authorization.getStatusCode() == HttpStatus.OK) {
			String message = (String) authorization.getBody().get("message");
			return "Autorizado".equalsIgnoreCase(message);
		} else return false;
	
	}
	
}
