package org.sklsft.commons.crypto.exception;

public class SignatureException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public SignatureException(String message) {
		super(message);

	}

	public SignatureException(String message, Throwable cause) {
		super(message, cause);
	}
}