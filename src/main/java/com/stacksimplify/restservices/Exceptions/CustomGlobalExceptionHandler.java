package com.stacksimplify.restservices.Exceptions;

import java.util.Date;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//applicable to all controllers
@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler{
	//overriding a method
	//Method Argument Not Valid Exception
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(),"from method argument not valid", ex.getLocalizedMessage());
				
		return new ResponseEntity<>(customErrorDetails,HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected  ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request){		
	
	CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(),"from method argument not valid", ex.getLocalizedMessage());
	
	return new ResponseEntity<>(customErrorDetails,HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	//Username not found exception
	@ExceptionHandler(UserNameNotFoundException.class)
	protected  ResponseEntity<Object> handleUserNameNotFoundException(UserNameNotFoundException ex, WebRequest request){		
		
		CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(),ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<>(customErrorDetails,HttpStatus.NOT_FOUND);
		}
	
	
	//Contraint Violation exception
	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<Object> handleConstraintVoilationException(ConstraintViolationException ex, WebRequest request){
		CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(),ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<>(customErrorDetails,HttpStatus.BAD_REQUEST);
	}
}
	

