package com.comcast.assignment.exception;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class LCSExceptionHandler extends ResponseEntityExceptionHandler {
	
	  
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(RequestNotValidException.class)
	public final ResponseEntity<ExceptionResponse> handleNoContentException(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse("Validation Exception", ex.getMessage());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		 String errorMessage = null ;		
		 List<FieldError> errors = ex.getBindingResult().getFieldErrors();
		    for (FieldError error : errors ) {		    	
		    	errorMessage = error.getObjectName() + " - " + error.getDefaultMessage();
		 }
		
		ExceptionResponse exceptionResponse = new ExceptionResponse("Validation Failed", errorMessage);
		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

}
