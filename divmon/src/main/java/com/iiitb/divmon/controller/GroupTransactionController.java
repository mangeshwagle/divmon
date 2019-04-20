package com.iiitb.divmon.controller;


import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iiitb.divmon.bean.Bill;
import com.iiitb.divmon.bean.GroupTransaction;
import com.iiitb.divmon.service.GroupTransactionService;

@RestController
public class GroupTransactionController 
{
	@Autowired
	private GroupTransactionService groupTransactionService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/addgrouptransaction")
	public void addGroupTransaction(@RequestParam List<Integer> borrowersList , @RequestParam int lenderId,
			@RequestParam  int groupId, @RequestParam  String description, @RequestParam String date, @RequestParam double amount)
	{
		Bill bill= new Bill();
		bill.setAmount(amount);
		bill.setDescription(description);
		Date dateTemp = new Date(date);
		bill.setDate(dateTemp);
		
		List<GroupTransaction> groupTransactions = new LinkedList<GroupTransaction>();
		for(int borrowerId : borrowersList)
		{
		GroupTransaction groupTransaction = new GroupTransaction();
		groupTransaction.setAmount(amount);
		groupTransaction.setGroupId(groupId);
		groupTransaction.setLenderId(lenderId);
		groupTransaction.setShare((amount/borrowersList.size()));
		groupTransaction.setBorrowerId(borrowerId);
		groupTransactions.add(groupTransaction);
		}
		
		groupTransactionService.addTransaction(bill, groupTransactions);
		
		
	}
	
}
