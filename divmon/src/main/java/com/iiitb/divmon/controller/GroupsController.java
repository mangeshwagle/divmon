package com.iiitb.divmon.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class GroupsController
{

	@Autowired
	private GroupsService groupsService;

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET, value = "/creategroup/{name}/{userid}")
	public void createGroup(@PathVariable(name="userid") int userid,@PathVariable(name="name") String name )
	{
		Set<User> ust = new HashSet<User>();
		ust.add(userService.getUserById(userid));
		Groups groups = new Groups();
		groups.setName(name);
		groups.setUserSet(ust);
		groupsService.add(groups);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/addusertogroup")
	public ResponseEntity<Void> addUsersToGroup(@RequestParam int groupId, @RequestParam String email)
	{
		Groups group = groupsService.getGroupById(groupId);
		Set<User> ust = group.getUserSet();
		User user = userService.getUserByEmail(email);
		ust.add(user);
		group.setUserSet(ust);
		if (groupsService.add(group))
		{
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getusersofgroup/{id}")
	public Set<User> getUsersOfGroup(@PathVariable int id)
	{
		return groupsService.getUsersByGroupId(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getusercount/{id}")
	public int getUserCount(@PathVariable int id)
	{
		return groupsService.getUsersByGroupId(id).size();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/removeuserfromgroup/{groupId}/{userId}")
	public void removeUserFromGroup(@PathVariable int groupId, @PathVariable int userId)
	{
		Groups group = groupsService.getGroupById(groupId);
		Set<User> ust = group.getUserSet();
		User user = userService.getUserById(userId);
		ust.remove(user);
		group.setUserSet(ust);
		groupsService.add(group);
	}
}
