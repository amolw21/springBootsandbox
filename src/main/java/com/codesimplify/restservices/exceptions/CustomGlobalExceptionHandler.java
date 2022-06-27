package com.codesimplify.restservices.exceptions;

import java.util.Date;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
//@RestControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		CustomErrorDetails customErrorDetails=new CustomErrorDetails(new Date(), "From MethodArgumentNotValid Exception in GEH",ex.getMessage());
		//return super.handleMethodArgumentNotValid(ex, headers, status, request);
		return new ResponseEntity<>(customErrorDetails,HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		CustomErrorDetails customErrorDetails=new CustomErrorDetails(new Date(), "From HTTPRequestMethodNotSupportedException  in GEH-Method not allowed",ex.getMessage());
		//return super.handleMethodArgumentNotValid(ex, headers, status, request);
		return new ResponseEntity<>(customErrorDetails,HttpStatus.METHOD_NOT_ALLOWED);
	}
	//usernamenotfoundException
	@ExceptionHandler(UserNameNotFoundException.class)
	public final ResponseEntity<Object>handleUserNameNotFoundExeption(UserNameNotFoundException e,WebRequest request)
	{
		CustomErrorDetails customErrorDetails=new CustomErrorDetails(new Date(),e.getMessage(),request.getDescription(false));
		return new ResponseEntity<>(customErrorDetails,HttpStatus.NOT_FOUND);
	}
	//constraintViolation
	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e,WebRequest request)
	{
		CustomErrorDetails details=new CustomErrorDetails(new Date(),e.getMessage(),request.getDescription(false));
		return new ResponseEntity<>(details,HttpStatus.BAD_REQUEST);
	}
	
}
