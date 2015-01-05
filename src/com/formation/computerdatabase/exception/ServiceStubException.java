package com.formation.computerdatabase.exception;

public class ServiceStubException extends RuntimeException {
	
	private static final long serialVersionUID = 408850687147798196L;
	private static final String MESSAGE = "The following method was not overridden by your Service Implementation.";
	
	public ServiceStubException() {
		super(MESSAGE);
	}
	
	public ServiceStubException(String message) {
		super(message);
	}
	
	public ServiceStubException(Exception e) {
		super(MESSAGE, e);
	}
	
	public ServiceStubException(String message, Exception e) {
		super(message, e);
	}

}
