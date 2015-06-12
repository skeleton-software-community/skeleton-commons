package org.sklsft.commons.api.exception.rights;

import org.sklsft.commons.api.exception.ApplicationException;

/**
 * Exception thrown by rights manager if a user is rejected when accessing a resource
 * 
 * @author Nicolas Thibault
 *
 */
public class AccessDeniedException extends ApplicationException {

	private static final long serialVersionUID = 1L;

	
	public AccessDeniedException(){
		super();
	}
	
	public AccessDeniedException(String message) {
		super(message);

	}

	public AccessDeniedException(String message, Throwable cause) {
		super(message, cause);
	}
}
