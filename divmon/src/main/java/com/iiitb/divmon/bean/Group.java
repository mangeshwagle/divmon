package com.iiitb.divmon.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Group
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@NotBlank
	String name;
	
	@ManyToMany
	Set<User> userSet = new HashSet<User>();

	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}

	public Set<User> getUserSet()
	{
		return userSet;
	}
	public void setUserSet(Set<User> userSet)
	{
		this.userSet = userSet;
	}
}
