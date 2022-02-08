package com.example.microservices.customerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.microservices.customerservice.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
