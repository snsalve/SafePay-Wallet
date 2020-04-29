package com.cg;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.entity.Customer;
import com.cg.repository.CustomerRepository;

@SpringBootTest
@DataJpaTest
class ParallelProjectWalletApplicationTests {

	@Autowired
	CustomerRepository customerRepository;
	@Test
	public void testRepository() {
		Customer customer = new Customer();
		customer.setName("Rahul");
		customer.setUsername("Rahul@123");
		
		assertEquals(true, customerRepository.save(customer));
		
		
	}

}
