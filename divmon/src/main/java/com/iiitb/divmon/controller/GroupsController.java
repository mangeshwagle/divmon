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

	@RequestMapping(method = RequestMethod.PUT, value = "/adduserstogroup/{id}")
	public void addUsersToGroup(@RequestParam List<String> emails, @PathVariable(name = "id") int groupId)
	{
		Groups groups = groupsService.getGroupById(groupId);
		Set<User> ust = groups.getUserSet();
		for (String email : emails)
		{
			User user = userService.getUserByEmail(email);
			ust.add(user);
		}
		groups.setUserSet(ust);
		groupsService.add(groups);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getusersofgroup/{id}")
	public Set<User> getUsersOfGroup(@PathVariable int id)
	{
		return groupsService.getUsersByGroupId(id);
	}
}
