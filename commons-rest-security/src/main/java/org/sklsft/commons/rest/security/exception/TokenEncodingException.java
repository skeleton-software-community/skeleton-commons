package org.sklsft.commons.rest.security.exception;

/**
 *
 */
public class TokenEncodingException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public TokenEncodingException(String message) {
		super(message);

	}

	public TokenEncodingException(String message, Throwable cause) {
		super(message, cause);
	}
}
