package org.sklsft.commons.rest.exception;

import org.sklsft.commons.api.exception.ApplicationException;

public class TestException extends ApplicationException {

	private static final long serialVersionUID = 1L;

	public TestException() {
	}

	public TestException(String message) {
		super(message);
	}

	public TestException(String message, Object details) {
		super(message, details);
	}

	public TestException(String message, Throwable cause) {
		super(message, cause);
	}

	public TestException(String message, Throwable cause, Object details) {
		super(message, cause, details);
	}

}
