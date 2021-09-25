package org.sklsft.commons.api.exception.repository;

import org.sklsft.commons.api.exception.ApplicationException;

/**
 * Exception thrown when a dao does not find a requested object
 * <br>Similar to hibernate ObjectNotFoundException
 * 
 * @author Nicolas Thibault
 *
 */
public class ObjectNotFoundException extends ApplicationException {

	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException() {
		super();
	}
	
	public ObjectNotFoundException(String message) {
		super(message);
	}

	public ObjectNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	@Override
	public String getHttpErrorCode() {
		return "404";
	}
}