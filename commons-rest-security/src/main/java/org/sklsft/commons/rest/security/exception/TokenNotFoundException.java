package org.sklsft.commons.rest.security.exception;

/**
 * 
 * Thrown if your token does not enable to retrieve valid credentials
 * 
 * @author Nicolas Thibault, Abdessalam El Jai, Alexandre Rupp
 *
 */
public class TokenNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public TokenNotFoundException(String message) {
		super(message);

	}

	public TokenNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
