package com.iiitb.divmon.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.iiitb.divmon.bean.GroupTransaction;

@Repository
public class GroupTransactionDAO
{
	@PersistenceContext
	private EntityManager entityManager;

	public List<GroupTransaction> findTransactionsOfUserInGroup(int userId, int groupId)
	{
		String hql = "FROM GroupTransaction AS gts WHERE ((gts.lenderId = ?1 OR gts.borrowerId = ?1) AND gts.groupId = ?2) ORDER BY gts.paid, gts.date DESC";
		
		@SuppressWarnings("unchecked")
		List<GroupTransaction> groupTransactionList = (List<GroupTransaction>) entityManager.createQuery(hql)
				.setParameter(1, userId).setParameter(2, groupId).getResultList();
		
		if(!groupTransactionList.isEmpty())
			return groupTransactionList;
		
		return null;
	}

}
