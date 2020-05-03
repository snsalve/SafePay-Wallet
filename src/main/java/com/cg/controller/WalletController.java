package com.cg.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.Customer;
import com.cg.entity.Transactions;
import com.cg.exceptions.InsufficientFundsException;
import com.cg.exceptions.UserNotFoundException;
import com.cg.service.WalletServiceInterface;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value= "/wallet")
public class WalletController {
	
	@Autowired
	WalletServiceInterface walletRepo;
	
	public WalletServiceInterface getWalletRepo() {
		return walletRepo;
	}

	public void setWalletRepo(WalletServiceInterface walletRepo) {
		this.walletRepo = walletRepo;
	}

	@GetMapping(value = "/getAll")
	public List<Customer> getUsers(){
		return walletRepo.getUsers();
	}
	
	@GetMapping(value = "/getUser/{username}")
	public Customer getUserById(@PathVariable("username")String uname) throws UserNotFoundException{
		return walletRepo.getUserById(uname);
	}
	
	@PostMapping(value = "/create")
	public boolean createUser(@RequestBody final Customer cust){
		return walletRepo.createUser(cust);
	}
	
	@PostMapping(value = "/update")
	public boolean updateUser(@RequestBody final Customer cust){
		return walletRepo.updateUser(cust);
	}
	
	@GetMapping(value="/doLogin/{mobile}/{password}")
	public Customer loginCredentials (@PathVariable("mobile")long mobile, @PathVariable("password")String pass) {
		return walletRepo.loginCredentials(mobile, pass);
	}
		
	@PostMapping(value="/doDeposit/{amount}")
	public boolean deposit(@RequestBody Customer custer, @PathVariable("amount")double amt) {
		return walletRepo.deposit(custer, amt);
	}
	
	@PostMapping(value="/doWithdraw/{amount}")
	public boolean withdraw(@RequestBody Customer custer, @PathVariable("amount")double amt) {
		return walletRepo.withdraw(custer, amt);
	}
	
	@GetMapping(value="/doTransfer/{mobile}/{amount}/{password}/{suserid}")
	public boolean Transfer (@PathVariable("mobile")long rmobNumber, @PathVariable("amount")double amount, @PathVariable("password")String pass, @PathVariable("suserid")String suname) {
		return walletRepo.Transfer(rmobNumber, amount, pass, suname);
	}
	
	@GetMapping(value = "/getTransactions/{username}")
	public List<Transactions> getTransaction(@PathVariable("username")String uname){
		return walletRepo.getTransaction(uname);
	}

}