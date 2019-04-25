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

import com.iiitb.divmon.bean.Friends;
import com.iiitb.divmon.bean.Transaction;
import com.iiitb.divmon.dao.TransactionDAO;
import com.iiitb.divmon.service.TransactionService;

@RestController
public class TransactionController
{
	@Autowired
	private TransactionService transactionService;

	@Autowired
	private TransactionDAO transactionDao;

	@RequestMapping(method = RequestMethod.POST, value = "/transaction")
	public void addTransaction(@RequestBody Transaction transaction)
	{
		try
		{
			transaction.setPaid(false);
			transactionService.addTransaction(transaction);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/borrow/{id}")
	public void borrowedMoneyById(@PathVariable int id)
	{
		List<Transaction> ts = transactionService.borrowedMoneyTransactions(id);
		HashMap<Integer, Double> map = new HashMap<>();
		for (Transaction x : ts)
		{
			if (map.containsKey(x.getLenderId()))
			{
				double temp = map.get(x.getLenderId());
				temp += x.getShare();
				map.put(x.getLenderId(), temp);
			}
			else
			{
				map.put(x.getLenderId(), x.getShare());
			}
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/borrowed/{id}")
	public void borrowedMoney(@PathVariable int id)
	{

		List<Transaction> transactions = transactionService.borrowedMoneyTransactions(id);
		Double borrowedMoney = 0D;
		for (Transaction ts : transactions)
		{
			borrowedMoney += ts.getShare();
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/lent/{id}")
	public void lentMoney(@PathVariable int id)
	{

		List<Transaction> transactions = transactionService.lentMoneyTransactions(id);
		Double lentMoney = 0D;
		for (Transaction ts : transactions)
		{
			lentMoney += ts.getShare();
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getall/{id1}/{id2}")
	public void getAllById(@PathVariable int id1, @PathVariable int id2)
	{

		List<Transaction> ts1 = transactionService.lentMoneyTransactions(id1);
		List<Transaction> ts2 = transactionService.lentMoneyTransactions(id2);
		List<Transaction> ts = new LinkedList<Transaction>();

		for (Transaction x : ts1)
		{
			if (x.getBorrowerId() == id2)
			{
				ts.add(x);
			}
		}
		for (Transaction x : ts2)
		{
			if (x.getBorrowerId() == id1)
			{
				ts.add(x);
			}
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/transactionlist/{friendId1}/{friendId2}")
	public List<Transaction> transactionList(@PathVariable int friendId1, @PathVariable int friendId2)
	{
		Friends friend = new Friends();
		friend.setUid1(friendId1);
		friend.setUid2(friendId2);
		return transactionDao.findTransactionsOfFriend(friend);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/settletransaction/{transactionId}")
	public void settleTransaction(@PathVariable int transactionId)
	{
		try
		{
			transactionService.settleTransaction(transactionId);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/settlealltransactions/{friendId1}/{friendId2}")
	public void settleAllTransaction(@PathVariable int friendId1, @PathVariable int friendId2)
	{
		try
		{
			Friends friend = new Friends();
			friend.setUid1(friendId1);
			friend.setUid2(friendId2);
			transactionService.settleAllTransaction(friend);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}	
}
