package com.iiitb.divmon.repository;

import org.springframework.data.repository.CrudRepository;

import com.iiitb.divmon.bean.User;

public interface UserRepository extends CrudRepository<User, Integer>
{
	public User findByEmail(String email);
}
