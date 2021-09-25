package org.sklsft.commons.rest.security.exception;

/**
 * 
 * Thrown if your token does not enable to retrieve valid credentials
 * 
 * @author Nicolas Thibault, Abdessalam El Jai, Alexandre Rupp
 *
 */
public class InvalidTokenException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public InvalidTokenException(String message) {
		super(message);

	}

	public InvalidTokenException(String message, Throwable cause) {
		super(message, cause);
	}
}
