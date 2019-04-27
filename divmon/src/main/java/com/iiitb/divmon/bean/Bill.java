package com.iiitb.divmon.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bill
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String description;
	double amount;
	Date date;
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public double getAmount()
	{
		return amount;
	}
	public void setAmount(double amount)
	{
		this.amount = amount;
	}
	
	public Date getDate()
	{
		return date;
	}
	public void setDate(Date date)
	{
		this.date = date;
	}
	@Override
	public String toString()
	{
		return "Bill [id=" + id + ", description=" + description + ", amount=" + amount + ", date=" + date + "]";
	}
}
