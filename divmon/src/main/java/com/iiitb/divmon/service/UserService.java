package com.iiitb.divmon.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
		User userFromDb = userRepository.findByEmail(user.getEmail()).orElse(null);
		if (userFromDb == null || !user.getPassword().equals(userFromDb.getPassword()))
		{
			return null;
		}
		userFromDb.setPassword("******");
		return userFromDb;
	}

	public List<User> getAllUser()
	{
		List<User> u = new ArrayList<User>();
		Iterable<User> i = userRepository.findAll();
		i.forEach(x -> u.add(x));

		return u;
	}

	public User getUserById(int id)
	{
		User u = userRepository.findById(id).orElse(null);
		return u;
	}

	public List<User> getAllUserById(List<Integer> ids)
	{
		List<User> users = new LinkedList<User>();
		Iterable<User> i = userRepository.findAllById(ids);
		i.forEach(x ->
		{
			x.setPassword("******");
			users.add(x);
		});

		return users;
	}

	public User getUserByEmail(String email)
	{
		User userFromDb = userRepository.findByEmail(email).orElse(null);

		return userFromDb;
	}

}
