package com.iiitb.divmon.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiitb.divmon.bean.Groups;
import com.iiitb.divmon.bean.User;
import com.iiitb.divmon.repository.GroupsRepository;

@Service
public class GroupsService
{

	@Autowired
	private GroupsRepository groupsRepository;

	public boolean add(Groups group)
	{
		try
		{
			groupsRepository.save(group);
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public Groups getGroupById(int groupId)
	{
		return groupsRepository.findById(groupId).get();

	}

	public Set<User> getUsersByGroupId(int gid)
	{
		Set<User> userSet = groupsRepository.findById(gid).get().getUserSet();
		return userSet;
	}

}
