package com.picpaySimplificado.project.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.picpaySimplificado.project.entites.User;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByUserDocument (User document); 
		
	
}
