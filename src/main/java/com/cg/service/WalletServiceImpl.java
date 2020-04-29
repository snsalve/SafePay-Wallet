package com.cg.service;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entity.Customer;
import com.cg.entity.Transactions;
import com.cg.repository.CustomerRepository;
@Service
public class WalletServiceImpl implements WalletServiceInterface{
	
	@Autowired
	CustomerRepository custRepo;
	
	public CustomerRepository getCustRepo() {
		return custRepo;
	}

	public void setCustRepo(CustomerRepository custRepo) {
		this.custRepo = custRepo;
	}

	@Override
	public List<Customer> getUsers() {
		return custRepo.findAll();
	}

	@Override
	public Optional<Customer> getUserById(String uname) {
		System.out.println("USERNAME:" + uname);
		Optional<Customer> cc =  custRepo.findById(uname);
		System.out.println("HI"+cc);
		return cc;
	}

	@Override
	public boolean createUser(Customer cust) {
		custRepo.save(cust);
		return true;
	}

	@Override
	public boolean updateUser(Customer cust) {
		String uname = cust.getUsername();
		Customer customer = custRepo.findById(uname).get();
		customer.setName(cust.getName());
		customer.setMail(cust.getMail());
		customer.setMob_number(cust.getMob_number());
		custRepo.save(customer);
		return true;
	}

	@Override
	public Customer loginCredentials(long mobile, String pass) {
		List<Customer> all = custRepo.findAll();
		String uname="";
		for (Customer customer : all) {
			if(customer.getMob_number()==mobile)
				uname=customer.getUsername();
		}
		 List<Customer> cust = custRepo.findByUsernameAndPassword(uname, pass);
		 if(cust.isEmpty())
			 return null;
	 else {
			 for (Customer customer : cust) {
				 if(customer.getPassword().equals(pass))
					 return customer;
			 }
			 return null;
		 }
	}

	@Override
	public boolean deposit(Customer custer, double amt) {
		Customer customer =  custRepo.findById(custer.getUsername()).get();
		customer.setBalance(customer.getBalance() + amt);
		
		java.util.Date javaDate = new java.util.Date();
		Date date = new Date(javaDate.getTime()); 
		
		customer.getTransactionList().add(new Transactions(customer.getUsername(), amt, "Deposit", date, customer.getBalance()));
		custRepo.save(customer);
		 return true;
	}

	@Override
	public boolean withdraw(Customer custer, double amt) {
		Customer customer =  custRepo.findById(custer.getUsername()).get();
		if(customer.getBalance() < amt)
			return false;
		else {
			customer.setBalance(customer.getBalance() - amt);

			java.util.Date javaDate = new java.util.Date();
			Date date = new Date(javaDate.getTime()); 
			
			customer.getTransactionList().add(new Transactions(customer.getUsername(), amt, "Withdraw", date, customer.getBalance()));
			custRepo.save(customer);
			return true;
		}
	}

	@Override
	public boolean Transfer(long rmobNumber, double amount, String pass, String suname) {
		List<Customer> all = custRepo.findAll();
		int flag = 0;
		String runame = "";
		Customer temp = custRepo.getOne(suname);
		for (Customer customer : all) {
			if((customer.getMob_number() == rmobNumber) && (temp.getMob_number() != rmobNumber)) {
					flag=1;
					runame = customer.getUsername();
			}	
		}
		if(flag==1) {
			Customer sender =custRepo.getOne(suname);
			Customer receiver =custRepo.getOne(runame);
			if(sender.getBalance()>amount) {
				sender.setBalance(sender.getBalance()-amount);
				receiver.setBalance(receiver.getBalance()+amount);
				
				java.util.Date javaDate = new java.util.Date();
				Date date = new Date(javaDate.getTime()); 
				
				sender.getTransactionList().add(new Transactions(sender.getUsername(), amount, "Transfer(Sent)", date, sender.getBalance()));
				receiver.getTransactionList().add(new Transactions(receiver.getUsername(), amount, "Transfer(Received)", date, receiver.getBalance()));

				custRepo.save(sender);
				custRepo.save(receiver);
				return true;
			}
			else
				return false;
		}
		else
		return false;
	}

	@Override
	public List<Transactions> getTransaction(String uname) {
		Customer customer =  custRepo.findById(uname).get();
		List<Transactions> tlist = customer.getTransactionList();
		Collections.reverse(tlist);
		return tlist;
	}

}
