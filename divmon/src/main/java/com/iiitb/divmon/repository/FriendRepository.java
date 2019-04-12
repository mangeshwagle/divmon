package com.iiitb.divmon.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.iiitb.divmon.bean.Friends;
import com.iiitb.divmon.bean.User;

public interface FriendRepository extends CrudRepository<Friends , Integer>
{
	public Iterable<Friends> findByUid1(int uid1);
}
