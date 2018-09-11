package com.namlee.examples.spring_examples.exception;

public class MyTransactionException extends Exception {

	private static final long serialVersionUID = 1L;

	public MyTransactionException(String message) {
		super(message);
	}

}
