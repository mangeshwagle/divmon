package com.iiitb.divmon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiitb.divmon.bean.Groups;
import com.iiitb.divmon.repository.GroupsRepository;

@Service
public class GroupsService {
	
	@Autowired
	private GroupsRepository groupsRepository;
	
	public void add(Groups group) {
		System.out.println(group);
		try
		{
			groupsRepository.save(group);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public Groups getGroupById(int groupId)
	{
		return	groupsRepository.findById(groupId).get();
		
		
	}
	public Groups getUsersByGroupId(int gid)
	{
		Groups group = groupsRepository.findById(gid).get();
		//System.out.println(group);
		
		return group;
		
	}
	
}
