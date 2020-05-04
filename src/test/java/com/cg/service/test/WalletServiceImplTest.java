package com.cg.service.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.cg.entity.Customer;
import com.cg.exceptions.UserNotFoundException;
import com.cg.service.WalletServiceInterface;

@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = Replace.NONE)

public class WalletServiceImplTest {
	
	@Autowired
	WalletServiceInterface walletService;
	
	private Customer getCustomer() {
		Customer customer = new Customer();
		customer.setName("Virat Kolhi");
		customer.setUsername("vkolhi18");
		customer.setMob_number(8547689547l);
		customer.setMail("virat@kolhi.com");
		customer.setPassword("Virat@123");
		customer.setBalance(5000);
		customer.setTransactionList(new ArrayList<>());
		return customer;
		
	}
	
	@Test
	public void createUserTest() {
		Customer customer = getCustomer();
		boolean result = walletService.createUser(customer);
		
		assertEquals(true, result);
	}
	
	@Test
	public void loginTest() throws UserNotFoundException {
		Customer customer = getCustomer();
		walletService.createUser(customer);
		Customer customerInDb = walletService.getUserById(customer.getUsername());
		Customer successCustomer = walletService.loginCredentials(customerInDb.getMob_number(), customerInDb.getPassword());
		
		assertEquals(successCustomer.getName(), customer.getName());

	}
	
	@Test
	public void updateUserTest() throws UserNotFoundException {
		Customer customer = getCustomer();
		walletService.createUser(customer);
		Customer oldCustomer = walletService.getUserById(customer.getUsername());
		oldCustomer.setName("Rahul Dravid");
		boolean result = walletService.updateUser(oldCustomer);
		
		assertEquals(true, result);

	}
	
	@Test
	public void getUserTest() throws UserNotFoundException {
		Customer customer = getCustomer();
		walletService.createUser(customer);
		Customer customerInDb = walletService.getUserById(customer.getUsername());
		Customer customerFromDb = walletService.getUserById(customerInDb.getUsername());
		
		assertEquals(customerFromDb, customerInDb);
		
	}
	
	@Test
	public void getAllUsersTest() {
		Customer customer = getCustomer();
		walletService.createUser(customer);
		
		assertNotNull(walletService.getUsers());

	}
	
	@Test
	public void depositTest() throws UserNotFoundException {
		Customer customer = getCustomer();
		walletService.createUser(customer);
		Customer customerInDb = walletService.getUserById(customer.getUsername());
		walletService.deposit(customerInDb, 1500);
		
		assertThat(walletService.getUserById(customerInDb.getUsername()).getBalance()).
			isEqualTo(getCustomer().getBalance()+1500);
	}
	
	@Test
	public void withdrawTest() throws UserNotFoundException {
		Customer customer = getCustomer();
		walletService.createUser(customer);
		Customer customerInDb = walletService.getUserById(customer.getUsername());
		walletService.withdraw(customerInDb, 1500);
		
		assertThat(walletService.getUserById(customerInDb.getUsername()).getBalance()).
			isEqualTo(getCustomer().getBalance()-1500);
	}
	
	@Test
	public void transferTest() throws UserNotFoundException {
		Customer customer = getCustomer();
		walletService.createUser(customer);
		Customer customerInDb = walletService.getUserById(customer.getUsername());
		walletService.Transfer(customerInDb.getMob_number(), 1500, customerInDb.getPassword(), customerInDb.getUsername());
		
		assertEquals(
				walletService.getUserById(customerInDb.getUsername()).getBalance() == (getCustomer().getBalance()-1500),
				walletService.getUserById(customerInDb.getUsername()).getBalance() == (getCustomer().getBalance()+1500)
				);
	}
	
	@Test
	public void transactionTest() throws UserNotFoundException {
		Customer customer = getCustomer();
		walletService.createUser(customer);
		Customer customerInDb = walletService.getUserById(customer.getUsername());
		walletService.deposit(customerInDb, 1500);
		walletService.withdraw(customerInDb, 1500);

		assertNotNull(walletService.getTransaction(customerInDb.getUsername()));
	}
}











