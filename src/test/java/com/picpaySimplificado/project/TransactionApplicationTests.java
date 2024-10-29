package com.picpaySimplificado.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.picpaySimplificado.project.dtos.TransactionDTO;
import com.picpaySimplificado.project.entites.Transaction;
import com.picpaySimplificado.project.entites.User;
import com.picpaySimplificado.project.enuns.UserRole;
import com.picpaySimplificado.project.repositories.TransactionRepository;
import com.picpaySimplificado.project.services.TransactionService;
import com.picpaySimplificado.project.services.UserService;

class TransactionApplicationTests {

    @InjectMocks
    private TransactionService transactionService; 

    @Mock
    private UserService userService; 

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private RestTemplate restTemplate;

    private User sender;
    private User receiver;
    private TransactionDTO transactionDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); 

        sender = new User(
            1L, 
            "John", 
            "Doe", 
            "12345678900", 
            "john.doe@example.com", 
            "password123", 
            new BigDecimal("100.00"), 
            UserRole.COMMON
        );

        receiver = new User(
            2L, 
            "Jane", 
            "Smith", 
            "98765432100", 
            "jane.smith@example.com", 
            "password123", 
            new BigDecimal("50.00"), 
            UserRole.MERCHANT
        );

        transactionDto = new TransactionDTO(new BigDecimal("30.00"), sender.getId(), receiver.getId());
    }

    @Test
    void testCreatedTransaction_Success() throws Exception {
        when(userService.findById(1L)).thenReturn(sender);
        when(userService.findById(2L)).thenReturn(receiver);

        transactionService.createdTransaction(transactionDto);

        verify(userService).validateTransaction(sender, new BigDecimal("30.00"));
        verify(transactionRepository).save(any(Transaction.class));
        verify(userService).saveUser(sender);
        verify(userService).saveUser(receiver);

        assertEquals(new BigDecimal("70.00"), sender.getBalance());
        assertEquals(new BigDecimal("80.00"), receiver.getBalance());
    }

    @Test
    void testAuthorizeTransaction_Success() {
        String url = "https://util.devi.tools/api/v2/authorize"; 
        
        ResponseEntity<Map> response = new ResponseEntity<>(Map.of("message", "Autorizado"), HttpStatus.OK);
        when(restTemplate.getForEntity(eq(url), eq(Map.class))).thenReturn(response);

        boolean result = transactionService.authorizeTransaction(sender, new BigDecimal("30.00"));

        assertTrue(result);
    }

    @Test
    void testAuthorizeTransaction_Failure() {
        String url = "https://util.devi.tools/api/v2/authorize"; 
        
        ResponseEntity<Map> response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        when(restTemplate.getForEntity(eq(url), eq(Map.class))).thenReturn(response);

        boolean result = transactionService.authorizeTransaction(sender, new BigDecimal("30.00"));

        assertFalse(result);
    }
}