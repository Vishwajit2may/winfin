package com.example.microservices.customerservice.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.transaction.Transactional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@EqualsAndHashCode
public class Passport {
	
	@Id
	@GeneratedValue
	private int passId;
	
	@Column(nullable = false)
	private String number;
	
//	@OneToOne(fetch = FetchType.LAZY, mappedBy = "passport")
//	private Customer customer;
	
	
}
