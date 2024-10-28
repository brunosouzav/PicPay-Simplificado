package com.picpaySimplificado.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.picpaySimplificado.project.entites.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}
