package com.iiitb.divmon.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "`groups`")
public class Groups
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@NotBlank
	String name;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_group",
    joinColumns = @JoinColumn (name = "group_id", referencedColumnName = "id"),   
    inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
	@JsonIgnore
	Set<User> userSet = new HashSet<User>();
	
	public Set<User> getUserSet() {
		return userSet;
	}
	public void setUserSet(Set<User> userSet) {
		this.userSet = userSet;
	}
	public int getId()
	{
		return id;
	}
	@Override
	public String toString() {
		return "Groups [id=" + id + ", name=" + name + ", userSet=" + userSet + "]";
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

}
