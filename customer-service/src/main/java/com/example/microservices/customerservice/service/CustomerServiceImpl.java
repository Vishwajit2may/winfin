package com.example.microservices.customerservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.microservices.customerservice.dto.OrderResponse;
import com.example.microservices.customerservice.entity.Customer;
import com.example.microservices.customerservice.repository.CustomerRepository;
import com.example.microservices.customerservice.repository.PassportRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private PassportRepository passportRepository;
	
	@Override
	public Customer addCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	@CachePut(cacheNames = "customers", key="#customer.id")
	public Customer updateCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	@Cacheable(cacheNames = "customers", key="#id")
	public Customer getCustomer(int id) {
		Optional<Customer> customer =  customerRepository.findById(id);
		
		if(customer.isPresent()) {
			return customer.get();
		} else 
			return new Customer();
	}

	@Override
	@CacheEvict(cacheNames = "customers", key="#id")
	public void deleteCustomer(int id) {
		customerRepository.deleteById(id);
	}

	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return customerRepository.findAll();
	}

	@Override
	public List<OrderResponse> getJoinInformation() {
		// TODO Auto-generated method stub
		return null;//customerRepository.getJoinInformation();
	}

	@Override
	public List<Customer> getCustomersInSortedOrder() {
		/*
		 * ArrayList<String> sorting = new
		 * ArrayList<String>(Arrays.asList("name","age"));
		 */
		Sort sort = Sort.by(Sort.Direction.DESC,"name","createdDate");
		return customerRepository.findAll(sort);
	}

	@Override
	public Page<Customer> getPaginatedCustomers(Integer pageNo, Integer pageSize) {
		PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
		return customerRepository.findAll(pageRequest);
	}

	
	
	

}
