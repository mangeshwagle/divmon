package com.iiitb.divmon.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints= {@UniqueConstraint(columnNames={"uid1","uid2"})})
public class Friends 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int uid1;
	private int uid2;
//	@OneToOne
//	private User user1;
//	
//	@OneToOne
//	private User user2;
	
	
//	public User getUser1() {
//		return user1;
//	}
//
//	public void setUser1(User user1) {
//		this.user1 = user1;
//	}
//
//	public User getUser2() {
//		return user2;
//	}
//
//	public void setUser2(User user2) {
//		this.user2 = user2;
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUid1() {
		return uid1;
	}

	public void setUid1(int uid1) {
		this.uid1 = uid1;
	}

	public int getUid2() {
		return uid2;
	}

	public void setUid2(int uid2) {
		this.uid2 = uid2;
	}

}
