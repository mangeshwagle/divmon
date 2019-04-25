package com.iiitb.divmon.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiitb.divmon.bean.Friends;
import com.iiitb.divmon.bean.Transaction;
import com.iiitb.divmon.dao.TransactionDAO;
import com.iiitb.divmon.repository.TransactionRepository;

@Service
public class TransactionService
{
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private TransactionDAO transactionDao;

	public void addTransaction(Transaction transaction)
	{
		transactionRepository.save(transaction);
	}

	public List<Transaction> borrowedMoneyTransactions(int id)
	{
		List<Transaction> transactions = new LinkedList<Transaction>();
		Iterable<Transaction> i = transactionRepository.findByBorrowerId(id);
		i.forEach(x -> transactions.add((Transaction) x));
		return transactions;
	}

	public List<Transaction> lentMoneyTransactions(int id)
	{
		List<Transaction> transactions = new LinkedList<Transaction>();
		Iterable<Transaction> i = transactionRepository.findByLenderId(id);
		i.forEach(x -> transactions.add((Transaction) x));
		return transactions;
	}
	
	public void settleTransaction(int transactionId) 
	{
		Transaction transaction = transactionRepository.findById(transactionId).get();
		transaction.setPaid(true);
		transactionRepository.save(transaction);
	}

	public void settleAllTransaction(Friends friend)
	{
		List<Transaction> transactionList = transactionDao.findTransactionsOfFriend(friend);
		for(Transaction trns : transactionList)
		{
			settleTransaction(trns.getId());
		}
	}
}
