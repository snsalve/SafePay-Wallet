package com.cg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cg.entity.Customer;
import com.cg.entity.Transactions;
@Service
public interface WalletServiceInterface {
	public List<Customer> getUsers();
	public Optional<Customer> getUserById(String uname);
	public boolean createUser(Customer cust);
	public boolean updateUser(Customer cust);
	public Customer loginCredentials (long mobile, String pass);
	public boolean deposit(Customer custer, double amt);
	public boolean withdraw(Customer custer,double amt);
	public boolean Transfer (long rmobNumber,double amount, String pass, String suname);
	public List<Transactions> getTransaction(String uname);

}
