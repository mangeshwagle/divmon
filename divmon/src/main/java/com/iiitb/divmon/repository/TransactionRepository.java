package com.iiitb.divmon.repository;

import org.springframework.data.repository.CrudRepository;

import com.iiitb.divmon.bean.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
	public Iterable<Transaction> findByBorrowerId(int borrowerId);
	public Iterable<Transaction> findByLenderId(int lenderId);
	//public List<Transaction> findTransactionsOfFriend(Friends friend);
	
}
