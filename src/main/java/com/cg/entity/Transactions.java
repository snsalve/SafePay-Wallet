package com.cg.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transactions implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int trans_id;
	@Column(name = "username")
	private String userName;
	private double amount;
	private String type;
	@Column(name = "transaction_date")
	private Date date;
	private double balance;
	public Transactions() {
		super();
	}

	public Transactions(String userName, double amount, String type, Date date, double balance) {
		super();
		this.userName = userName;
		this.amount = amount;
		this.type = type;
		this.date = date;
		this.balance = balance;
	}

	public int getTrans_id() {
		return trans_id;
	}
	public void setTrans_id(int trans_id) {
		this.trans_id = trans_id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Transactions [trans_id=" + trans_id + ", userName=" + userName + ", amount=" + amount + ", type=" + type
				+ ", date=" + date + ", balance=" + balance + "]";
	}
	 
	
}
