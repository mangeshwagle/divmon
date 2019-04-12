package com.iiitb.divmon.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "uid1", "uid2" }) })
public class Friends {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int uid1;
	private int uid2;

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
