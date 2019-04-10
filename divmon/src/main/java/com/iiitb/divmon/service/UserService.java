package com.iiitb.divmon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiitb.divmon.bean.User;
import com.iiitb.divmon.repository.UserRepository;

@Service
public class UserService
{
	@Autowired
	private UserRepository userRepository;

	public void addUser(User user)
	{
		userRepository.save(user);
	}

	public User verifyLogin(User user)
	{
		User userFromDb = userRepository.findByEmail(user.getEmail());
		if (user.getPassword().equals(userFromDb.getPassword()))
		{
			System.out.println(userFromDb);
			userFromDb.setPassword("******");
			return userFromDb;
		}
		return null;
	}

}
