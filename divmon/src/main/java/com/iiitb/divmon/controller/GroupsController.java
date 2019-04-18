package com.iiitb.divmon.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iiitb.divmon.bean.Groups;
import com.iiitb.divmon.bean.User;
import com.iiitb.divmon.service.GroupsService;
import com.iiitb.divmon.service.UserService;

@RestController
public class GroupsController {
	
	@Autowired
	private GroupsService groupsService;
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/creategroup")
	public void createGroup(@RequestParam Groups groups)
	{
		
//		Set<User> ust = new HashSet<User>();
//		ust.add(userService.getUserById(1));
//		ust.add(userService.getUserById(2));
//		Groups g = new Groups();
//		g.setName("test");
//		g.setUserSet(ust);
		groupsService.add(groups);
	}
	@RequestMapping(method = RequestMethod.GET, value = "/getusersofgroup/{id}")
	public Set<User> getUsersOfGroup(@PathVariable int id)
	{
		Groups group = groupsService.getUsersByGroupId(id);
		Set<User> users =group.getUserSet();
		return users;
		
	}

}
