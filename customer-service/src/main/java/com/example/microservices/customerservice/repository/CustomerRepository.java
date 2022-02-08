package com.example.microservices.customerservice.repository;

import java.util.List;

import javax.persistence.NamedNativeQueries;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.example.microservices.customerservice.dto.OrderResponse;
import com.example.microservices.customerservice.entity.Customer;

@Repository
//@RepositoryRestResource(path="customersjpa")
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

//	@Query("SELECT new com.example.microservices.customerservice.dto.OrderResponse( c.name, p.productName) FROM Customer c JOIN c.products p")
//	public List<OrderResponse> getJoinInformation();
	
	//public Customer findById(int id);
	
	public Customer deleteById(int id);

	@Query("SELECT MAX(id) from Customer")
	public int getMaxId();
	/*
	 * @Transactional public Customer updateCustomer(Customer customer);
	 */
	
	
	
}
