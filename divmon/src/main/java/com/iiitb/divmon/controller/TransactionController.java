package com.iiitb.divmon.controller;

import java.util.HashMap;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iiitb.divmon.bean.FriendTotal;
import com.iiitb.divmon.bean.Transaction;
import com.iiitb.divmon.repository.FriendRepository;
import com.iiitb.divmon.repository.TransactionDAO;
import com.iiitb.divmon.service.FriendService;
import com.iiitb.divmon.service.TransactionService;

@RestController
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private TransactionDAO transactionDao;
	
	@Autowired
	private FriendRepository friendRepositroy;

	@RequestMapping(method = RequestMethod.POST, value = "/transaction")
	public void addTransaction(@RequestBody Transaction transaction) {
		transaction.setPaid(false);
		transactionService.addTransaction(transaction);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/borrowed/{id}")
	public void borrowedMoney(@PathVariable int id) {

		List<Transaction> ts = transactionService.borrowedMoneyTransactions(id);
		for (Transaction x : ts) {
			System.out.println(x);
		}

	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/friendsTransactionList/{id}")
	public List<FriendTotal> friendsTransactionList(@PathVariable int id) {

		return transactionService.FriendsTransaction(id);

	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/transactionlist/{friendId}")
	public List<Transaction> transactionList(@PathVariable int friendId) {
		return transactionDao.findTransactionsOfFriend(friendRepositroy.findById(friendId).orElse(null));

	}

}
