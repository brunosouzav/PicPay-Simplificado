package com.picpaySimplificado.project;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.picpaySimplificado.project.entites.User;
import com.picpaySimplificado.project.enuns.UserRole;
import com.picpaySimplificado.project.repositories.UserRepository;
import com.picpaySimplificado.project.services.UserService;
import com.picpaySimplificado.project.services.exceptions.InsufficientBalanceException;


class ProjectApplicationTests {
	
	@Mock
	private UserRepository repository;
	
	@InjectMocks
	private UserService service; 
	
	private User user;
	
	   @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this); 
	        user = new User();
	        user.setId(1L);
	        user.setFisrtName("Bruno");
	        user.setBalance(new BigDecimal("100.00"));
	        user.setRole(UserRole.COMMON);
	    }


	   @Test
	    void testValidateTransaction_ValidTransaction() throws Exception {
	        BigDecimal amount = new BigDecimal("50.00");
	        
	        assertDoesNotThrow(() -> service.validateTransaction(user, amount));
	    }
	   
	   @Test
	   void testValidateTransaction_InsufficientBalance() {
	       BigDecimal amount = new BigDecimal("150.00");  
	       Exception exception = assertThrows(
	           InsufficientBalanceException.class,
	           () -> service.validateTransaction(user, amount)
	       );

	       assertEquals("Insufficient balance", exception.getMessage());
	   }
}
