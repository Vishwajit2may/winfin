package com.example.microservices.currencyexchangeservice;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.microservices.currencyexchangeservice.bean.ExchangeValue;

@RestController
public class CurrencyExchangeController {

	Logger logger = LoggerFactory.getLogger(this.getClass()); 
	@Autowired
	private Environment environment;
	
	//@Autowired
	//private ExchangeValueRepository repository;
	
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retriveExchangeValue(@PathVariable String from, @PathVariable String to) {
		
		ExchangeValue exchangeValue = null;//repository.findByFromAndTo(from, to);
		//ExchangeValue exchangeValue = new ExchangeValue(1000L,from,to,BigDecimal.valueOf(82));
		exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		logger.info("==>"+exchangeValue);
		return exchangeValue;
		
	}
	@GetMapping("/message")
	public String retriveMessage() {
		
		return "Hello winFin service!";
		
	}
}
