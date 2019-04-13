package com.iiitb.divmon.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iiitb.divmon.bean.Friends;
import com.iiitb.divmon.bean.Transaction;
@Transactional
@Repository 
public class TransactionDAO {
	@PersistenceContext	
	private EntityManager entityManager;
	
	public List<Transaction> findTransactionsOfFriend(Friends friend) {
		
		
		// TODO Auto-generated method stub
		//String hql = "FROM transaction as ts where (ts.uid1 = "+friend.getUid1()+" and ts.uid2 ="+friend.getUid2()+") or "+"(ts.uid2 = "+friend.getUid1()+" and ts.uid1 ="+friend.getUid2()+")" ;
		 String hql = "FROM Transaction AS ts WHERE (ts.borrowerId = ?1 AND ts.lenderId = ?2 ) OR (ts.borrowerId = ?2 AND ts.lenderId = ?1) ORDER BY ts.date";
		 List<Transaction> transactionList = (List<Transaction>) entityManager.createQuery(hql).setParameter(1,friend.getUid1()).setParameter(2,friend.getUid2()).getResultList();
		 return transactionList;		 
	}

}
