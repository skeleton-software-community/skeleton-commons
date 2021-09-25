package org.sklsft.commons.api.exception.state;

import org.sklsft.commons.api.exception.ApplicationException;

/**
 * Exception thrown by state manager if an operation on an object is impossible due to its state
 * 
 * @author Nicolas Thibault
 *
 */
public class InvalidStateException extends ApplicationException {

	private static final long serialVersionUID = 1L;

	public InvalidStateException(String message) {
		super(message);
	}

	public InvalidStateException(String message, Throwable cause) {
		super(message, cause);
	}
}