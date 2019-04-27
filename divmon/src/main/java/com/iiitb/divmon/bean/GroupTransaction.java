package com.iiitb.divmon.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GroupTransaction
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int billId;
	private int groupId;
	private int borrowerId;
	private int lenderId;
	
	@Column(columnDefinition = "BOOLEAN DEFAULT false")
	private boolean paid = false;
	
	private double amount;
	private double share;
	private Date date;
	private String description;

	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}

	public int getBillId()
	{
		return billId;
	}
	public void setBillId(int billId)
	{
		this.billId = billId;
	}

	public int getBorrowerId()
	{
		return borrowerId;
	}
	public void setBorrowerId(int borrowerId)
	{
		this.borrowerId = borrowerId;
	}

	public int getLenderId()
	{
		return lenderId;
	}
	public void setLenderId(int lenderId)
	{
		this.lenderId = lenderId;
	}

	public boolean isPaid()
	{
		return paid;
	}
	public void setPaid(boolean paid)
	{
		this.paid = paid;
	}

	public double getAmount()
	{
		return amount;
	}
	public void setAmount(double amount)
	{
		this.amount = amount;
	}

	public double getShare()
	{
		return share;
	}
	public void setShare(double share)
	{
		this.share = share;
	}

	public int getGroupId()
	{
		return groupId;
	}
	public void setGroupId(int groupId)
	{
		this.groupId = groupId;
	}
	public Date getDate()
	{
		return date;
	}
	public void setDate(Date date)
	{
		this.date = date;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	@Override
	public String toString()
	{
		return "GroupTransaction [id=" + id + ", billId=" + billId + ", groupId=" + groupId + ", borrowerId="
				+ borrowerId + ", lenderId=" + lenderId + ", paid=" + paid + ", amount=" + amount + ", share=" + share
				+ ", date=" + date + "]";
	}
}
