package com.cg.repository.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.cg.entity.Customer;
import com.cg.repository.CustomerRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class DataRepositoryTest {
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	TestEntityManager entityManager;
	
	@Test
	public void createUserTest() {
		Customer customer = new Customer("Rahul", "klrahul", "rahul@123", "rahul@gmail.com", 8547562548l);
		Customer customer1 = entityManager.persist(customer);
		Customer customer2 = customerRepository.getOne(customer1.getUsername());

		assertEquals(customer2, customer1);
	}
	
	@Test
	public void findAllUsers() {
		Customer customerA = new Customer("Rahul", "klrahul", "rahul@123", "rahul@gmail.com", 8547562548l);
		Customer customerB = new Customer("Sachin", "sachint", "sachin@123", "sachin@gmail.com", 7548659235l);
		entityManager.persist(customerA);
		entityManager.persist(customerB);
		
		List<Customer> allUsers = customerRepository.findAll();
		List<Customer> list = new ArrayList<>();
		for (Customer customer : allUsers) {
			if(customer.getUsername().equals("klrahul") || customer.getUsername().equals("sachint")) 
				list.add(customer);
		}
		assertThat(list.size()).isEqualTo(2);
	}
	
	@Test
	public void updateUserTest() {
		Customer customer = new Customer("Rahul", "klrahul", "rahul@123", "rahul@gmail.com", 8547562548l);
		entityManager.persist(customer);
		
		Customer customer1 = customerRepository.getOne("klrahul");
		customer1.setPassword("klrahul@1");
		entityManager.persist(customer1);
		
		assertThat(customer.getPassword()).isEqualTo("klrahul@1");
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
