package com.iiitb.divmon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iiitb.divmon.bean.User;
import com.iiitb.divmon.service.UserService;

@RestController
public class UserController
{

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.POST, value = "/register")
	public void addUser(@RequestBody User user)
	{
		userService.addUser(user);
		System.out.println(user);
	}

	@RequestMapping("/login")
	public ResponseEntity<User> login(@RequestBody User user)
	{
		User userFromDb = userService.verifyLogin(user);
		System.out.println(user);
		System.out.println(userFromDb);

		if (userFromDb != null)
			return new ResponseEntity<User>(userFromDb, HttpStatus.OK);

		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	}
}
