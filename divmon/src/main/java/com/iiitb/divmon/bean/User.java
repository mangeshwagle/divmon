package com.iiitb.divmon.bean;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Fetch;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class User
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@NotBlank
	String name;
	
	@NotBlank
	@Column(unique = true)
	String email;
	
	@NotBlank
	String password; 
	
	@ManyToMany(mappedBy="userSet" , fetch = FetchType.EAGER)
	@JsonManagedReference
	 Set<Groups> groupSet;
	
	public Set<Groups> getGroupSet() {
		return groupSet;
	}
	public void setGroupSet(Set<Groups> groupSet) {
		this.groupSet = groupSet;
	}
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
	
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	@Override
	public String toString()
	{
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + "]";
	}
}
