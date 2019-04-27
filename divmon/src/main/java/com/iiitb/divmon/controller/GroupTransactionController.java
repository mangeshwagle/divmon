package com.iiitb.divmon.controller;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iiitb.divmon.bean.Bill;
import com.iiitb.divmon.bean.GroupTransaction;
import com.iiitb.divmon.bean.User;
import com.iiitb.divmon.service.GroupTransactionService;
import com.iiitb.divmon.service.GroupsService;

@RestController
public class GroupTransactionController
{
	@Autowired
	private GroupTransactionService groupTransactionService;
	
	@Autowired
	private GroupsService groupsService;

	@RequestMapping(method = RequestMethod.POST, value = "/addgrouptransaction")
	public void addGroupTransaction (@RequestBody GroupTransaction transaction)
	{
		Bill bill = new Bill();
		bill.setAmount(transaction.getAmount());
		bill.setDescription(transaction.getDescription());
		Date dateTemp = transaction.getDate();
		bill.setDate(dateTemp);
		
		Set<User> borrowerSet = groupsService.getUsersByGroupId(transaction.getGroupId());
		
		System.out.println(bill);

		List<GroupTransaction> groupTransactions = new LinkedList<GroupTransaction>();
		for (User borrower : borrowerSet)
		{
			if(borrower.getId() == transaction.getLenderId())
				continue;
			GroupTransaction groupTransaction = new GroupTransaction();
			groupTransaction.setAmount(transaction.getAmount());
			groupTransaction.setGroupId(transaction.getGroupId());
			groupTransaction.setLenderId(transaction.getLenderId());
			groupTransaction.setShare((transaction.getAmount() / (borrowerSet.size())));
			groupTransaction.setBorrowerId(borrower.getId());
			groupTransaction.setDate(dateTemp);
			groupTransaction.setDescription(transaction.getDescription());
			groupTransactions.add(groupTransaction);
			
			System.out.println(groupTransaction);
		}

		groupTransactionService.addTransaction(bill, groupTransactions);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/usergrouptransactionlist/{userId}/{groupId}")
	public List<GroupTransaction> userGroupTransactionList(@PathVariable int userId, @PathVariable int groupId)
	{
		return groupTransactionService.showTransactionsOfUser(userId, groupId);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/settlegrouptransaction/{transactionId}")
	public void settleGroupTransaction(@PathVariable int transactionId)
	{
		try
		{
			groupTransactionService.settleGroupTransaction(transactionId);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	@RequestMapping(method = RequestMethod.GET, value = "/settleallgrouptransactions/{userId}/{groupId}")
	public void settleAllTransaction(@PathVariable int userId, @PathVariable int groupId)
	{
		try
		{
			groupTransactionService.settleAllGroupTransaction(userId, groupId);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
