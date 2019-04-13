package com.iiitb.divmon.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

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

	@RequestMapping(method = RequestMethod.GET, value = "/borrow/{id}")
	public void borrowedMoneyById(@PathVariable int id) {

		List<Transaction> ts = transactionService.borrowedMoneyTransactions(id);
		HashMap<Integer, Double> map = new HashMap<>();
		for (Transaction x : ts) {
			if (map.containsKey(x.getLenderId())) {
				double temp = map.get(x.getLenderId());
				temp += x.getShare();
				map.put(x.getLenderId(), temp);
			} else {
				map.put(x.getLenderId(), x.getShare());
			}
		}
		System.out.println(map);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/borrowed/{id}")
	public void borrowedMoney(@PathVariable int id) {

		List<Transaction> ts = transactionService.borrowedMoneyTransactions(id);
		Double borrowedMoney = 0D;
		for (Transaction x : ts) {
			borrowedMoney += x.getShare();
		}
		System.out.println(borrowedMoney);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/lent/{id}")
	public void lentMoney(@PathVariable int id) {

		List<Transaction> ts = transactionService.lentMoneyTransactions(id);
		Double lentMoney = 0D;
		for (Transaction x : ts) {
			lentMoney += x.getShare();
		}
		System.out.println(lentMoney);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/total/{id}")
	public void totalMoney(@PathVariable int id) {

		List<Transaction> ts1 = transactionService.lentMoneyTransactions(id);
		List<Transaction> ts2 = transactionService.borrowedMoneyTransactions(id);
		Double lentMoney = 0D;
		Double borrowedMoney = 0D;
		for (Transaction x : ts1) {
			lentMoney += x.getShare();
		}
		for (Transaction x : ts2) {
			borrowedMoney += x.getShare();
		}
		double total = borrowedMoney - lentMoney;
		if (total < 0) {
			System.out.println("You are owed: " + Math.abs(total));
		} else {
			System.out.println("You owe: " + total);
		}

	}

	@RequestMapping(method = RequestMethod.GET, value = "/getll/{id1}/{id2}")
	public void getAllById(@PathVariable int id1, @PathVariable int id2) {

		List<Transaction> ts1 = transactionService.lentMoneyTransactions(id1);
		List<Transaction> ts2 = transactionService.lentMoneyTransactions(id2);
		List<Transaction> ts = new LinkedList<Transaction>();

		for (Transaction x : ts1) {
			if (x.getBorrowerId() == id2) {
				ts.add(x);
			}
		}
		for (Transaction x : ts2) {
			if (x.getBorrowerId() == id1) {
				ts.add(x);
			}
		}
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
