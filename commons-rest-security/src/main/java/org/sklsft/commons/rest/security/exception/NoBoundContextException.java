package org.sklsft.commons.rest.security.exception;

/**
 * If you try to access a security context and there is no one
 * 
 * @author Nicolas Thibault, Abdessalam El Jai, Alexandre Rupp
 *
 */
public class NoBoundContextException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NoBoundContextException(String message) {
		super(message);

	}

	public NoBoundContextException(String message, Throwable cause) {
		super(message, cause);
	}
}
