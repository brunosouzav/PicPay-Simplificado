package com.picpaySimplificado.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.picpaySimplificado.project.dtos.NotificationDTO;
import com.picpaySimplificado.project.entites.User;
import com.picpaySimplificado.project.services.NotificationService;


class NotificationApplicationTests {
	
	 @InjectMocks
	    private NotificationService notificationService;

	    @Mock
	    private RestTemplate restTemplate;

	    private User user;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);

	        
	        user = new User();
	        user.setEmail("user@example.com");
	    }

	    @Test
	    void testSendNotification_Success() throws Exception {
	        
	        ResponseEntity<String> response = new ResponseEntity<>("Notificação enviada", HttpStatus.OK);
	        when(restTemplate.postForEntity(any(String.class), any(NotificationDTO.class), eq(String.class)))
	            .thenReturn(response);

	        
	        notificationService.sendNotification(user, "Mensagem de teste");

	    
	        verify(restTemplate, times(1)).postForEntity(any(String.class), any(NotificationDTO.class), eq(String.class));
	    }

	    @Test
	    void testSendNotification_Failure() {
	       
	        ResponseEntity<String> response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        when(restTemplate.postForEntity(any(String.class), any(NotificationDTO.class), eq(String.class)))
	            .thenReturn(response);

	        
	        Exception exception = assertThrows(Exception.class, () -> {
	            notificationService.sendNotification(user, "Mensagem de teste");
	        });

	       
	        assertEquals("Serviço de notificação fora do ar", exception.getMessage());
	    }
	}