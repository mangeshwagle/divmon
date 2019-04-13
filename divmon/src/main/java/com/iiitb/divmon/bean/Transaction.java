package com.iiitb.divmon.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int lenderId;
	private int borrowerId;
	private double amount;
	private double share;
	private boolean paid;
	private String description;
	private Date date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLenderId() {
		return lenderId;
	}

	public void setLenderId(int lenderId) {
		this.lenderId = lenderId;
	}

	public int getBorrowerId() {
		return borrowerId;
	}

	public void setBorrowerId(int borrowerId) {
		this.borrowerId = borrowerId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getShare() {
		return share;
	}

	public void setShare(float share) {
		this.share = share;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", lenderId=" + lenderId + ", borrowerId=" + borrowerId + ", amount=" + amount
				+ ", share=" + share + ", paid=" + paid + ", description=" + description + ", date=" + date + "]";
	}
	
	

}
