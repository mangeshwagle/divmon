package com.iiitb.divmon.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iiitb.divmon.bean.Friends;
import com.iiitb.divmon.bean.User;
import com.iiitb.divmon.service.FriendService;
import com.iiitb.divmon.service.UserService;

@RestController
public class FriendController {
	@Autowired
	private FriendService friendService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/addfriend")
	public ResponseEntity <Void>addFriend(@RequestBody Friends f)
	{
        	if(friendService.addFriend(f))
        	{
        		return new ResponseEntity<Void>(HttpStatus.OK);
        	}
    		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);		
	}
	

	

	
	@RequestMapping(method = RequestMethod.GET, value = "/showfriends/{id}")
	public List<User> showFriends(@PathVariable int id)
	{
		System.out.println("hello" + id);
		
		List<Integer> ids = friendService.showFriendsId(id);
		List<User> friends = userService.getAllUserById(ids);
		return friends;

	}
	
}
