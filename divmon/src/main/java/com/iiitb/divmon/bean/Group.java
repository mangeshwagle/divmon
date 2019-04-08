package com.iiitb.divmon.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Group
{
	@Id
	int id;
	@NotBlank
	String name;
	
	@ManyToMany
	Set<User> userSet = new HashSet<User>();
}
