package com.example.microservices.currencyexchangeservice.config;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeoutException;

import org.apache.http.HttpStatus;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;

public class CircuitBreakerConfiguration {

	
	public CircuitBreaker config() {
		// Create a custom configuration for a CircuitBreaker
		CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
		  .failureRateThreshold(50)
		  .slowCallRateThreshold(50)
		  .waitDurationInOpenState(Duration.ofMillis(1000))
		  .slowCallDurationThreshold(Duration.ofSeconds(2))
		  .permittedNumberOfCallsInHalfOpenState(3)
		  .minimumNumberOfCalls(10)
		  .slidingWindowType(SlidingWindowType.TIME_BASED)
		  .slidingWindowSize(5)
//		  .recordException(e -> INTERNAL_SERVER_ERROR
//		                 .equals(getResponse().getStatus()))
		  .recordExceptions(IOException.class, TimeoutException.class)
		  //.ignoreExceptions(BusinessException.class, OtherBusinessException.class)
		  .build();

		// Create a CircuitBreakerRegistry with a custom global configuration
		CircuitBreakerRegistry circuitBreakerRegistry = 
		  CircuitBreakerRegistry.of(circuitBreakerConfig);

		// Get or create a CircuitBreaker from the CircuitBreakerRegistry 
		// with the global default configuration
//		CircuitBreaker circuitBreakerWithDefaultConfig = 
//		  circuitBreakerRegistry.circuitBreaker("name1");

		// Get or create a CircuitBreaker from the CircuitBreakerRegistry 
		// with a custom configuration
		CircuitBreaker circuitBreakerWithCustomConfig = circuitBreakerRegistry
		  .circuitBreaker("name2", circuitBreakerConfig);
		return circuitBreakerWithCustomConfig;
	}
}
