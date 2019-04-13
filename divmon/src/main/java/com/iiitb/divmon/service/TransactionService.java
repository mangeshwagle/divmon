package com.iiitb.divmon.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iiitb.divmon.bean.FriendTotal;
import com.iiitb.divmon.bean.Friends;
import com.iiitb.divmon.bean.Transaction;
import com.iiitb.divmon.bean.User;
import com.iiitb.divmon.repository.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private UserService userService;

	public void addTransaction(Transaction transaction) {
		transactionRepository.save(transaction);
	}

	public List<Transaction> borrowedMoneyTransactions(int id) {
		List<Transaction> transactions = new LinkedList<Transaction>();
		Iterable<Transaction> i = transactionRepository.findByBorrowerId(id);
		i.forEach(x -> transactions.add((Transaction) x));
		return transactions;

	}
	
	public List<FriendTotal> FriendsTransaction(int id)
	{
		List<Transaction> transactions = new LinkedList<Transaction>();
		Iterable<Transaction> i = transactionRepository.findByBorrowerId(id);
		i.forEach(x -> transactions.add((Transaction) x));
		List<FriendTotal> friendsTotal = new ArrayList<FriendTotal>();
		HashMap<Integer , FriendTotal> h = new HashMap<Integer , FriendTotal>();
		for(Transaction transaction : transactions)
		{
			if(transaction.isPaid()== false)
			{
				int lender = transaction.getLenderId();
				if(h.containsKey(lender))
				{
					double total = h.get(lender).getTotal();
					total = total + transaction.getShare();
					h.get(lender).setTotal(total);
				}
				else
				{
					FriendTotal friendTotal = new FriendTotal();
					User user = userService.getUserById(lender);
					friendTotal.setUser(user);
					System.out.println(transaction.getShare());
					friendTotal.setTotal(+transaction.getShare());
					h.put(lender, friendTotal);
				}
			}
		}
		
		List<Transaction> transactions1 = new LinkedList<Transaction>();
		i = transactionRepository.findByLenderId(id);
		i.forEach(x -> transactions1.add((Transaction) x));
		for(Transaction transaction : transactions1)
		{	
			if(transaction.isPaid() == false)
			{
				int borrower = transaction.getBorrowerId();
				if(h.containsKey(borrower))
				{
					double total = h.get(borrower).getTotal();
					total = total - transaction.getShare();
					h.get(borrower).setTotal(total);
				}
				else
				{
					FriendTotal friendTotal = new FriendTotal();
					User user = userService.getUserById(borrower);
					friendTotal.setUser(user);
					friendTotal.setTotal(-transaction.getShare());
					h.put(borrower, friendTotal);
				}
			}
		}
		for(int key:h.keySet())
		{
			friendsTotal.add(h.get(key));
		}
		return friendsTotal;
		
	}
	
	public List<Transaction> lentMoneyTransactions(int id) {
		List<Transaction> transactions = new LinkedList<Transaction>();
		Iterable<Transaction> i = transactionRepository.findByLenderId(id);
		i.forEach(x -> transactions.add((Transaction) x));
		return transactions;

	}
}
