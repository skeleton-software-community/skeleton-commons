package org.sklsft.commons.rest.security.exception;

public class CredentialsConflictException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public CredentialsConflictException(String message) {
		super(message);

	}

	public CredentialsConflictException(String message, Throwable cause) {
		super(message, cause);
	}
}
