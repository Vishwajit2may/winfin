package com.example.microservices.customerservice.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.microservices.customerservice.entity.Customer;
import com.example.microservices.customerservice.service.CustomerService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CustomerRepositoryTest {

	@Autowired
	private CustomerRepository repository;
	
	@Autowired
	private CustomerService customerService;
	
	
	
	@Test
	//@BeforeTestExecution
	public void getMaxId() {
		int ids = repository.getMaxId();
		assertNotEquals(0, ids);
	}
	@Test
	void findById() {
		
		int id = repository.getMaxId();
		Customer customer = repository.getOne(id);
		assertEquals(id, customer.getId());
	}
	
	@Test
	@DirtiesContext
	public void deleteById() {
		//assertEquals(true,repository.findById(id).isPresent());
		int ids = repository.getMaxId();
		repository.deleteById(ids);
		Customer customer =  repository.getOne(ids);
		assertEquals(ids,customer.getId());
		
	}

	@Test
	public void saveTest() {
		Customer customer = repository.getOne(18);
		customer = repository.save(customer);
		
		assertNotEquals(0, customer.getId());
		
	}
}
