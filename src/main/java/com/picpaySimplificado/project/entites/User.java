package com.picpaySimplificado.project.entites;

import java.io.Serializable;
import java.math.BigDecimal;

import com.picpaySimplificado.project.dtos.UserDTO;
import com.picpaySimplificado.project.enuns.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class User implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	@Column(unique = true)
	private String document;
	
	@Column(unique = true)
	private String email;
	
	private String password;
	
	private BigDecimal balance;
	
	@Enumerated(EnumType.STRING)
	private UserRole role;
	
	public User(UserDTO user) {
		this.firstName = user.firstName();
		this.lastName = user.lastName();
		this.document = user.document();
		this.email = user.email();
		this.password = user.password();
		this.balance = user.balance();
		this.role = user.role();
		
	
	}

}
