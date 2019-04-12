package com.iiitb.divmon.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiitb.divmon.bean.Friends;
import com.iiitb.divmon.bean.User;
import com.iiitb.divmon.repository.FriendRepository;

@Service
public class FriendService {

	@Autowired
	private FriendRepository friendRepository;
	@Autowired
	private UserService userService;

	public boolean addFriend(Friends friend) {
		try {
			if (friend.getUid1() != friend.getUid2()) {
				if (friendRepository.save(friend) != null) {
					Friends reverseFriend = new Friends();
					reverseFriend.setUid1(friend.getUid2());
					reverseFriend.setUid2(friend.getUid1());
					if (friendRepository.save(reverseFriend) != null) {
						return true;
					}
				}
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Friends> getAllFriends(User user) {
		List<Friends> friends = new LinkedList<Friends>();
		Iterable<Friends> i = friendRepository.findByUid1(user.getId());
		i.forEach(x -> friends.add((Friends) x));
		return friends;

	}

	public List<Integer> showFriendsId(int id) {
		System.out.println("hello" + id);
		User user = userService.getUserById(id);
		List<Friends> friends = getAllFriends(user);
		List<Integer> friendIds = new LinkedList<Integer>();

		for (Friends f : friends) {
			friendIds.add(f.getUid2());
		}
		return friendIds;
	}
}
