package com.cg;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import com.cg.entity.Customer;
import com.cg.repository.CustomerRepository;

@DataJpaTest
public class DataRepositoryTest {
	@Autowired
	CustomerRepository customerRepository;
	
	public void testRepository() {
		Customer customer = new Customer();
		customer.setName("Rahul");
		customer.setUsername("Rahul@123");
		
		customerRepository.save(customer);
		
		Assertions.assertNotNull(customer.getMob_number());
	}

}
