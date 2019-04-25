package com.iiitb.divmon.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.iiitb.divmon.bean.Friends;
import com.iiitb.divmon.bean.Transaction;

@Repository
public class TransactionDAO
{
	@PersistenceContext
	private EntityManager entityManager;

	public List<Transaction> findTransactionsOfFriend(Friends friend)
	{
		String hql = "FROM Transaction AS ts WHERE ((ts.borrowerId = ?1 AND ts.lenderId = ?2 ) OR (ts.borrowerId = ?2 AND ts.lenderId = ?1)) ORDER BY ts.date";
		
		@SuppressWarnings("unchecked")
		List<Transaction> transactionList = (List<Transaction>) entityManager.createQuery(hql)
				.setParameter(1, friend.getUid1()).setParameter(2, friend.getUid2()).getResultList();
		return transactionList;
	}

}
