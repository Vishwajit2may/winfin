package com.example.microservices.customerservice.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.microservices.customerservice.dto.OrderRequest;
import com.example.microservices.customerservice.dto.OrderResponse;
import com.example.microservices.customerservice.entity.Customer;
import com.example.microservices.customerservice.exceptions.CustomerNotFoundException;
import com.example.microservices.customerservice.repository.CustomerRepository;
import com.example.microservices.customerservice.repository.ProductRepository;
import com.example.microservices.customerservice.service.CustomerService;

import ch.qos.logback.core.status.Status;

@RestController
@RequestMapping("orders")
public class OrderController {

	

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ProductRepository productRepository;

	@PostMapping("/placeOrder")
	public ResponseEntity placeOrder(@Valid @RequestBody OrderRequest orderRequest) {

		Customer customer = customerService.addCustomer(orderRequest.getCustomer());
		
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("orders/findOrder/{id}")
				.buildAndExpand(customer.getId()).toUri();

		return ResponseEntity.created(location).build();

	}

	@PutMapping("/updateOrder")
	public ResponseEntity updateOrder(@Valid @RequestBody OrderRequest orderRequest) {

		Customer customer = customerService.updateCustomer(orderRequest.getCustomer());
		
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("orders/findOrder/{id}")
				.buildAndExpand(customer.getId()).toUri();

		return ResponseEntity.ok(customer);

	}

	@GetMapping("/findOrder/{id}")
	public Customer findOrder(@PathVariable int id) {
		Customer customer =  customerService.getCustomer(id);
		if(null == customer) {
 			throw new CustomerNotFoundException("Customer not found "+id);
		}
		//20250127
		return customer;
	}
	
	@DeleteMapping("/{id}")
	public void deleteOrder(@PathVariable int id) {
		customerService.deleteCustomer(id);
	}

	@GetMapping("/findAllOrders")
	public List<Customer> findAllOrder() {
		return customerService.getAllCustomers();
	}

	@GetMapping("/findAllInfo")
	public List<OrderResponse> getJoinInformation() {
		return customerService.getJoinInformation();
	}

}
