package com.picpaySimplificado.project.dtos;

import java.math.BigDecimal;

import com.picpaySimplificado.project.enuns.UserRole;

public record UserDTO(String firtsName, String lastName, String document, BigDecimal balance, String email, String password, UserRole role) {

}
