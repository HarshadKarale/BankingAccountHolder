package com.banking.account.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<String> handleAccountNotFound(AccountNotFoundException ex) {
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InSufficientBalanceException.class)
	public ResponseEntity<String> handleInSufficientBalance(InSufficientBalanceException ex){
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler
	public ResponseEntity<String> handleNegativeAmountException(NegativeAmountException ex){
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
