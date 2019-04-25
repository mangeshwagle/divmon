package com.iiitb.divmon.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iiitb.divmon.bean.Groups;
import com.iiitb.divmon.bean.User;
import com.iiitb.divmon.service.UserService;

@RestController
public class UserController
{
	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.POST, value = "/register")
	public void register(@RequestBody User user)
	{
		userService.addUser(user);
	}

	@RequestMapping("/login")
	public ResponseEntity<User> login(@RequestBody User user)
	{
		User userFromDb = userService.verifyLogin(user);

		if (userFromDb != null)
			return new ResponseEntity<User>(userFromDb, HttpStatus.OK);

		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping("/allusers")
	public List<User> getAllUser()
	{
		return userService.getAllUser();

	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getgroupssofuser/{id}")
	public Set<Groups> getGroupsOfUser(@PathVariable int id)
	{
		return userService.getGroupsByUserId(id);
	}
}
