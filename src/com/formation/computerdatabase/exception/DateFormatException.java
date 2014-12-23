package com.formation.computerdatabase.exception;

public class DateFormatException extends RuntimeException {
	
	private static final long serialVersionUID = 408850687147798196L;
	private static final String MESSAGE = "Wrong date format provideds.";
	
	public DateFormatException() {
		super(MESSAGE);
	}
	
	public DateFormatException(String message) {
		super(message);
	}
	
	public DateFormatException(Exception e) {
		super(MESSAGE, e);
	}
	
	public DateFormatException(String message, Exception e) {
		super(message, e);
	}

}
