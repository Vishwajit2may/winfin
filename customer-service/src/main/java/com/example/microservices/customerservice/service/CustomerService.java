package com.example.microservices.customerservice.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.microservices.customerservice.dto.OrderResponse;
import com.example.microservices.customerservice.entity.Customer;

public interface CustomerService {

	public Customer addCustomer(Customer customer);
	
	public Customer updateCustomer(Customer customer);
	
	public Customer getCustomer(int id);
	
	public void deleteCustomer(int id);

	public List<Customer> getAllCustomers();

	public List<OrderResponse> getJoinInformation();
	
	public List<Customer> getCustomersInSortedOrder();
	
	public Page<Customer> getPaginatedCustomers(Integer pageNo, Integer pageSize) ;
}
