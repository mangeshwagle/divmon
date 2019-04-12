package com.iiitb.divmon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiitb.divmon.bean.Transaction;
import com.iiitb.divmon.repository.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;
	
	public void addTransaction(Transaction transaction)
	{
		transactionRepository.save(transaction);
	}
	
}
