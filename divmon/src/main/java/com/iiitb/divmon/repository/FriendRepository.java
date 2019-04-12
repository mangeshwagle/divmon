package com.iiitb.divmon.repository;

import org.springframework.data.repository.CrudRepository;

import com.iiitb.divmon.bean.Friends;

public interface FriendRepository extends CrudRepository<Friends , Integer>
{
	public Iterable<Friends> findByUid1(int uid1);
}
