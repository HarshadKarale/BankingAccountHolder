package com.banking.account.exception;

public class InSufficientBalanceException extends RuntimeException{
	public InSufficientBalanceException(String msg) {
		super(msg);
	}
}
