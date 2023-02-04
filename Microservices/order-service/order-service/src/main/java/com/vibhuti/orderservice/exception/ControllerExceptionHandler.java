package com.vibhuti.orderservice.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.vibhuti.orderservice.dto.ErrorMessage;

@RestControllerAdvice
public class ControllerExceptionHandler {
	@ExceptionHandler(OutOfStockException.class)
	public ResponseEntity<ErrorMessage> resourceNotFoundException(OutOfStockException ex, WebRequest request) {
		ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND.value(), new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
	}

}
