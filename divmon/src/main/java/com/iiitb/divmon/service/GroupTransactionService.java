package com.iiitb.divmon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiitb.divmon.bean.Bill;
import com.iiitb.divmon.bean.GroupTransaction;
import com.iiitb.divmon.dao.GroupTransactionDAO;
import com.iiitb.divmon.repository.BillRepository;
import com.iiitb.divmon.repository.GroupTransactionRepository;

@Service
public class GroupTransactionService 
{
	@Autowired
	private GroupTransactionRepository groupTransactionRepository;
	
	@Autowired
	private GroupTransactionDAO groupTransactionDao;
	
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
	
	public List<GroupTransaction> showTransactionsOfUser(int userId, int groupId)
	{	
		return groupTransactionDao.findTransactionsOfUserInGroup(userId, groupId);
	}

	public void settleGroupTransaction(int transactionId)
	{
		GroupTransaction groupTransaction = groupTransactionRepository.findById(transactionId).get();
		groupTransaction.setPaid(true);
		groupTransactionRepository.save(groupTransaction);
		
	}

	public void settleAllGroupTransaction(int userId, int groupId)
	{
		List<GroupTransaction> groupTransactionList = groupTransactionDao.findTransactionsOfUserInGroup(userId, groupId);
		for(GroupTransaction trns : groupTransactionList)
		{
			settleGroupTransaction(trns.getId());
		}
	}
}
