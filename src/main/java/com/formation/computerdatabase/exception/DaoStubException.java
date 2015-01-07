package com.formation.computerdatabase.exception;

public class DaoStubException extends RuntimeException {
	
	private static final long serialVersionUID = 408850687147798196L;
	private static final String MESSAGE = "The following method was not overridden by your Dao Implementation.";
	
	public DaoStubException() {
		super(MESSAGE);
	}
	
	public DaoStubException(String message) {
		super(message);
	}
	
	public DaoStubException(Exception e) {
		super(MESSAGE, e);
	}
	
	public DaoStubException(String message, Exception e) {
		super(message, e);
	}

}
