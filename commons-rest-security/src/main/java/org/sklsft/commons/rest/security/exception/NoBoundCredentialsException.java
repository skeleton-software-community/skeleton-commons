package org.sklsft.commons.rest.security.exception;

/**
 * If you try to access a security context and there is no one
 * 
 * @author Nicolas Thibault, Abdessalam El Jai, Alexandre Rupp
 *
 */
public class NoBoundCredentialsException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NoBoundCredentialsException(String message) {
		super(message);

	}

	public NoBoundCredentialsException(String message, Throwable cause) {
		super(message, cause);
	}
}
