package com.picpaySimplificado.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.picpaySimplificado.project.dtos.NotificationDTO;
import com.picpaySimplificado.project.entites.User;

@Service
public class NotificationService {

	@Autowired
	private RestTemplate restTemplate;
	
	public void sendNotification(User user, String mensage) throws Exception {
		/* 
		
		String email = user.getEmail();
		NotificationDTO notificationRequest = new NotificationDTO(email, mensage);
		
		ResponseEntity<String> notificationResponse = 
				restTemplate.postForEntity("https://util.devi.tools/api/v1/notify", notificationRequest, String.class);
	
		if (!(notificationResponse.getStatusCode() ==  HttpStatus.OK)) {
			throw new Exception("Serviço de notificação fora do ar");
		*/
		}
	}

