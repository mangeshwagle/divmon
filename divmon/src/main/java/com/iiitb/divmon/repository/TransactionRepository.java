package com.iiitb.divmon.repository;

import org.springframework.data.repository.CrudRepository;

import com.iiitb.divmon.bean.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

}