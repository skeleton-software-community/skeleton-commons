package org.sklsft.commons.rest.security.exception;

/**
 * 
 * This exception is thrown if you try to bind a security context if there is already one
 * 
 * @author Nicolas Thibault, Abdessalam El Jai, Alexandre Rupp
 *
 */
public class CredentialsConflictException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public CredentialsConflictException(String message) {
		super(message);

	}

	public CredentialsConflictException(String message, Throwable cause) {
		super(message, cause);
	}
}
