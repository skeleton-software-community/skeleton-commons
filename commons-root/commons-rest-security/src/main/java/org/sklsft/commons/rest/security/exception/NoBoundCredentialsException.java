package org.sklsft.commons.rest.security.exception;

public class NoBoundCredentialsException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NoBoundCredentialsException(String message) {
		super(message);

	}

	public NoBoundCredentialsException(String message, Throwable cause) {
		super(message, cause);
	}
}
