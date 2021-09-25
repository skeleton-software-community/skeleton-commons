package org.sklsft.commons.crypto.exception;

public class CryptingException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public CryptingException(String message) {
		super(message);

	}

	public CryptingException(String message, Throwable cause) {
		super(message, cause);
	}
}