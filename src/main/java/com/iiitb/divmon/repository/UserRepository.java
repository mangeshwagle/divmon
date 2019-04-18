package com.iiitb.divmon.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.iiitb.divmon.bean.User;

public interface UserRepository extends CrudRepository<User, Integer>
{
	public Optional<User> findByEmail(String email);
}
