package com.example.microservices.customerservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import com.example.microservices.customerservice.entity.Customer;
import com.example.microservices.customerservice.repository.CustomerRepository;

@SpringBootTest(classes= {CustomerServiceTest.class})
public class CustomerServiceTest {

	@Mock
	private CustomerRepository customerRepo;
	
	@InjectMocks
	private CustomerServiceImpl customerService;
	
	public List<Customer> listCust = new ArrayList<Customer>();
	
	@Test
	@Order(1)
	public void getAllCustomerTest() {
		
		List<Customer> listCust = new ArrayList<Customer>();
		listCust.add(new Customer(1,"Vish","vishwajit2may@gmail.com","Male",32,LocalDateTime.now(),LocalDateTime.now()));
		listCust.add(new Customer(2,"Viren","viren18nov@gmail.com","Male",5,LocalDateTime.now(),LocalDateTime.now()));
				
		
		when(customerRepo.findAll()).thenReturn(listCust);//Mocking
		List<Customer> listCustRepo = customerService.getAllCustomers();
		System.out.println(listCustRepo);
		assertEquals(2, customerService.getAllCustomers().size()); 
	}
	
	@Test
	@Order(2)
	public void getCutomerById(){
		List<Customer> listCust = new ArrayList<Customer>();
		listCust.add(new Customer(1,"Vish","vishwajit2may@gmail.com","Male",32,LocalDateTime.now(),LocalDateTime.now()));
		listCust.add(new Customer(2,"Viren","viren18nov@gmail.com","Male",5,LocalDateTime.now(),LocalDateTime.now()));
		int id = 1;
		Optional<Customer> customer = listCust.stream().filter(cust->cust.getId()==id).findFirst();
		
		
		when(customerRepo.findById(id)).thenReturn(customer);
		assertEquals(customer.get().getEmail(), customerService.getCustomer(id).getEmail());
		
	}
	
	@Test
	@Order(3)
	public void getCutomerByIdFindAll(){
		List<Customer> listCust = new ArrayList<Customer>();
		listCust.add(new Customer(1,"Vish","vishwajit2may@gmail.com","Male",32,LocalDateTime.now(),LocalDateTime.now()));
		listCust.add(new Customer(2,"Viren","viren18nov@gmail.com","Male",5,LocalDateTime.now(),LocalDateTime.now()));
		int id = 1;
		when(customerRepo.findAll()).thenReturn(listCust);
		assertEquals(id, customerService.getCustomer(id).getId());
	}
}
