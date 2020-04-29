package com.cg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String>{
	
	public List<Customer> findByUsernameAndPassword(String Username, String Pass);
	
}
