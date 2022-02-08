package com.example.microservices.customerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.microservices.customerservice.entity.Passport;

@Repository
public interface PassportRepository extends JpaRepository<Passport, Integer>{

}
