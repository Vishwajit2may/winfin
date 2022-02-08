package com.example.microservices.customerservice.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.example.microservices.customerservice.dto.OrderRequest;
import com.example.microservices.customerservice.entity.Customer;
import com.example.microservices.customerservice.repository.CustomerRepository;
import com.example.microservices.customerservice.repository.ProductRepository;

@SpringBootTest
class OrderControllerTest {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Test
	void testPlaceOrder() {
		Customer cust = new Customer();
		cust.setName("Vish");
		cust.setGender("Male");
		cust.setEmail("vish.junit@gmail.com");
		OrderRequest request= new OrderRequest();
		request.setCustomer(cust);
		/*
		 * OrderController orderController = new OrderController();
		 * ResponseEntity<Customer> response = orderController.placeOrder(request);
		 */
		cust = customerRepository.save(cust);
		assertNotNull(customerRepository.getOne(cust.getId()));
		System.out.println("This is place order test");
		//fail("Not yet implemented");
	}

	@Test
	void testUpdateOrder() {
		String str ="abc";
		String str1="abc";
		String str2 = new String("abc");
		assertEquals(true, (str == str1));
		assertEquals(false, (str == str2));
		assertEquals(true, (str.equals(str2)));
		//fail("Not yet implemented");
	}

	@Test
	void testFindOrder() {
//		fail("Not yet implemented");
		List<Customer> customers = customerRepository.findAll();
//		assertE;
		
	}

	@Test
	void testDeleteOrder() {
//		fail("Not yet implemented");
	}

	@Test
	void testFindAllOrder() {
//		fail("Not yet implemented");
	}

	@Test
	void testGetJoinInformation() {
//		fail("Not yet implemented");
	}

}
