package com.assessement.britishtime.exception;

//Custom exception for invalid time format.
public class InvalidTimeFormatException extends RuntimeException {
	public InvalidTimeFormatException(String message) {
		super(message);
	}

	public InvalidTimeFormatException(String message, Throwable cause) {
		super(message, cause);
	}
}