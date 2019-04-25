package com.iiitb.divmon.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiitb.divmon.bean.FriendTotal;
import com.iiitb.divmon.bean.Friends;
import com.iiitb.divmon.bean.Transaction;
import com.iiitb.divmon.bean.User;
import com.iiitb.divmon.repository.FriendRepository;
import com.iiitb.divmon.repository.TransactionRepository;

@Service
public class FriendService
{
	@Autowired
	private FriendRepository friendRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TransactionRepository transactionRepository;

	public boolean addFriend(Friends friend)
	{
		try
		{
			if (friend.getUid1() != friend.getUid2())
			{
				if (friendRepository.save(friend) != null)
				{
					Friends reverseFriend = new Friends();
					reverseFriend.setUid1(friend.getUid2());
					reverseFriend.setUid2(friend.getUid1());
					if (friendRepository.save(reverseFriend) != null)
					{
						return true;
					}
				}
			}
			return false;
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public List<Friends> getAllFriends(User user)
	{
		List<Friends> friends = new LinkedList<Friends>();
		Iterable<Friends> i = friendRepository.findByUid1(user.getId());
		i.forEach(x -> friends.add((Friends) x));
		return friends;
	}

	public List<Integer> showFriendsId(int id)
	{
		User user = userService.getUserById(id);
		List<Friends> friends = getAllFriends(user);
		List<Integer> friendIds = new LinkedList<Integer>();

		for (Friends f : friends)
		{
			friendIds.add(f.getUid2());
		}
		return friendIds;
	}
	
	public List<FriendTotal> friendsTransactionTotal(int id)
	{
		List<Transaction> transactions = new LinkedList<Transaction>();
		Iterable<Transaction> i = transactionRepository.findByBorrowerId(id);
		i.forEach(x -> transactions.add((Transaction) x));
		List<FriendTotal> friendsTotal = new ArrayList<FriendTotal>();
		HashMap<Integer, FriendTotal> h = new HashMap<Integer, FriendTotal>();
		Iterable<Friends> j = friendRepository.findByUid1(id);

		j.forEach(friend ->
							{
								FriendTotal friendTotal = new FriendTotal();
								User user = userService.getUserById(friend.getUid2());
								friendTotal.setUser(user);
								friendTotal.setTotal(0);
								h.put(friend.getUid2(), friendTotal);
							});

		for (Transaction transaction : transactions)
		{
			if (transaction.isPaid() == false)
			{
				int lender = transaction.getLenderId();
				double total = h.get(lender).getTotal();
				total = total + transaction.getShare();
				h.get(lender).setTotal(total);

			}
		}

		List<Transaction> transactions1 = new LinkedList<Transaction>();
		i = transactionRepository.findByLenderId(id);
		i.forEach(x -> transactions1.add((Transaction) x));
		for (Transaction transaction : transactions1)
		{
			if (transaction.isPaid() == false)
			{
				int borrower = transaction.getBorrowerId();
				double total = h.get(borrower).getTotal();
				total = total - transaction.getShare();
				h.get(borrower).setTotal(total);
			}
		}
		for (int key : h.keySet())
		{
			friendsTotal.add(h.get(key));
		}
		return friendsTotal;
	}
}
