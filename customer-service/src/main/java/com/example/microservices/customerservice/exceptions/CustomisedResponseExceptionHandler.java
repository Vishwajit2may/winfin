package com.example.microservices.customerservice.exceptions;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomisedResponseExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
		return new ResponseEntity(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(CustomerNotFoundException.class)
	public final ResponseEntity<Object> handleCustomerNotFoundException(CustomerNotFoundException ex, WebRequest request) throws Exception {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
		return new ResponseEntity(exceptionResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(TimeoutException.class)
	public final ResponseEntity<Object> handleCustomerNotFoundException(TimeoutException ex, WebRequest request) throws Exception {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
		return new ResponseEntity(exceptionResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public final ResponseEntity<Object> handleCustomerNotFoundException(MethodArgumentNotValidException ex) throws Exception {
		List<FieldError> errorList = ex.getBindingResult().getFieldErrors();
		
		String errorMessage = errorList.stream().map(fieldError->fieldError.getField()+" - "+fieldError.getDefaultMessage()).sorted().collect(Collectors.joining(", "));
		
		return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
	}
	
	/*
	 * @ExceptionHandler(BadCredentialsException.class) public final
	 * ResponseEntity<Object> handleCustomerNotFoundException(TimeoutException ex,
	 * WebRequest request) throws Exception { ExceptionResponse exceptionResponse =
	 * new ExceptionResponse(new
	 * Date(),ex.getMessage(),request.getDescription(false)); return new
	 * ResponseEntity(exceptionResponse,HttpStatus.NOT_FOUND); }
	 */
	
	
}
