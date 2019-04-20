package com.iiitb.divmon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiitb.divmon.bean.Bill;
import com.iiitb.divmon.bean.GroupTransaction;
import com.iiitb.divmon.repository.BillRepository;
import com.iiitb.divmon.repository.GroupTransactionRepository;

@Service
public class GroupTransactionService 
{
	@Autowired
	private GroupTransactionRepository groupTransactionRepository;
	@Autowired
	private BillRepository billRepository;
	
	public void addTransaction(Bill bill, List<GroupTransaction> groupTransactions)
	{
		billRepository.save(bill);
		
		for(GroupTransaction groupTransaction : groupTransactions)
		{
		groupTransaction.setBillId(bill.getId());
		groupTransactionRepository.save(groupTransaction);
	
		}
	}
}
