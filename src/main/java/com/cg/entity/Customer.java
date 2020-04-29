package com.cg.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Customer implements Serializable{
	//Test Commet
	private static final long serialVersionUID = 1L;
	private String name;
	@Id
	private String username;
	private String password;
	private double balance;
	private String mail;
	private long mob_number;
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name = "username", referencedColumnName = "username")
	private List<Transactions> transactionList;
	
	public Customer() {
		
	}
	
	public Customer(String name, String username, String password, String mail, long mob_number) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.mail = mail;
		this.mob_number = mob_number;
		balance=0.0;
		transactionList=new ArrayList<>();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}	
	
	public long getMob_number() {
		return mob_number;
	}

	public void setMob_number(long mob_number) {
		this.mob_number = mob_number;
	}

	public List<Transactions> getTransactionList() {
		return transactionList;
	}

	public void setTransactionList(List<Transactions> transactionList) {
		this.transactionList = transactionList;
	}

	@Override
	public String toString() {
		return "Customer [name=" + name + ", username=" + username + ", password=" + password + ", balance=" + balance
				+ ", mail=" + mail + ", mob_number=" + mob_number + "]";
	}		
	
	/*
	 * public void addTransaction(Transactions t) { t.setCustomer(this);
	 * this.getTlist().add(t); }
	 */
}
	

