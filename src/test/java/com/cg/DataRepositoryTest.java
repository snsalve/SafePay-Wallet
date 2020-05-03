package com.cg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import com.cg.entity.Customer;
import com.cg.repository.CustomerRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class DataRepositoryTest {
	@Autowired
	CustomerRepository customerRepository;

	
	@Test
	public void createUserTest() {
		Customer customer = new Customer("Rahul", "klrahul", "rahul@123", "rahul@gmail.com", 8547562548l);
		customerRepository.save(customer);
		Customer customer1 = customerRepository.getOne("klrahul");
		assertNotNull(customer);
		assertEquals(customer.getName(), customer1.getName());
		assertEquals(customer.getMail(), customer1.getMail());
	}
	
	@Test
	public void findAllUsers() {
		List<Customer> allUsers = customerRepository.findAll();
		assertNotNull(allUsers);
	}
	
	@Test
	public void updateUserTest() {
		Customer customer = new Customer("Rahul", "klrahul", "rahul@123", "rahul@gmail.com", 8547562548l);
		customerRepository.save(customer);
		Customer customer1 = customerRepository.getOne("klrahul");
		customer1.setMail("klrahul@gmail.com");
		customer1.setName("Lokesh Rahul");
		customerRepository.save(customer1);
		assertNotEquals(customer.getMail(), customer1.getMail());
		assertNotEquals(customer.getName(), customer1.getName());

	}
	
	@Test
	public void deleteUserTest() {
		Customer customer = new Customer("Rahul", "klrahul", "rahul@123", "rahul@gmail.com", 8547562548l);
		customerRepository.save(customer);
		System.out.println(customer);
		customerRepository.delete(customer);
		boolean customerCheck = customerRepository.existsById("klrahul");
		assertEquals(false, customerCheck);
	}

}
