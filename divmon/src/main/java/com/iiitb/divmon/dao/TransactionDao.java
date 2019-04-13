//package com.iiitb.divmon.dao;
//
//import java.util.List;
//import java.util.Optional;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.iiitb.divmon.bean.Friends;
//import com.iiitb.divmon.bean.Transaction;
//import com.iiitb.divmon.repository.TransactionRepository;
//
//@Transactional
//@Repository
//public class TransactionDao implements TransactionRepository {
//	@PersistenceContext
//	private EntityManager entityManager;
//
//	@Override
//	public <S extends Transaction> S save(S entity) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <S extends Transaction> Iterable<S> saveAll(Iterable<S> entities) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Optional<Transaction> findById(Integer id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public boolean existsById(Integer id) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public Iterable<Transaction> findAll() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Iterable<Transaction> findAllById(Iterable<Integer> ids) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public long count() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public void deleteById(Integer id) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void delete(Transaction entity) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void deleteAll(Iterable<? extends Transaction> entities) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void deleteAll() {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public Iterable<Transaction> findByBorrowerId(int borrowerId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Iterable<Transaction> findByLenderId(int lenderId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<Transaction> findTransactionsOfFriend(Friends friend) {
//
//		// TODO Auto-generated method stub
//		String hql = "FROM transaction as ts where (ts.uid1 = " + friend.getUid1() + " and ts.uid2 =" + friend.getUid2()
//				+ ") or " + "(ts.uid2 = " + friend.getUid1() + " and ts.uid1 =" + friend.getUid2() + ")";
//		return (List<Transaction>) entityManager.createQuery(hql).getResultList();
//	}
//
//}