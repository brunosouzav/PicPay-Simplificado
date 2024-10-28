package com.picpaySimplificado.project.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picpaySimplificado.project.entites.User;
import com.picpaySimplificado.project.enuns.UserRole;
import com.picpaySimplificado.project.repositories.UserRepository;
import com.picpaySimplificado.project.services.exceptions.InsufficientBalanceException;
import com.picpaySimplificado.project.services.exceptions.UnauthorizedTransactionException;
import com.picpaySimplificado.project.services.exceptions.UserNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public void validateTransaction(User sender, BigDecimal amount) throws Exception {
	
		if(sender.getRole() == UserRole.MERCHANT) {
			throw new UnauthorizedTransactionException("User not authorized to make transaction");
		}
		
		if (sender.getBalance().compareTo(amount) <0) {
			throw new InsufficientBalanceException("Insufficient balance");
		}
	}

	public User findById(Long id) throws Exception {
		return this.repository.findById(id).orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));
    }
	
	public void saveUser(User user) {
		this.repository.save(user);
	}
}



