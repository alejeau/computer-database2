package com.formation.computerdatabase.exception;

public class PersistenceException extends RuntimeException {

	private static final long serialVersionUID = 5492319475892079018L;
	private static final String MESSAGE = "An error occured during transation.";

	public PersistenceException() {
		super(MESSAGE);
	}

	public PersistenceException(String message) {
		super(message);
	}

	public PersistenceException(Exception e) {
		super(MESSAGE, e);
	}

	public PersistenceException(String message, Exception e) {
		super(message, e);
	}

}
