package org.sklsft.commons.api.exception.validation;

import org.sklsft.commons.api.exception.ApplicationException;

/**
 * Exception thrown when arguments are not valid. See javax.validation support by Spring MVC
 * 
 * @author Nicolas Thibault
 *
 */
public class InvalidArgumentException extends ApplicationException {

	private static final long serialVersionUID = 1L;
	
	public static final String INVALID_ARGUMENTS = "invalid.arguments";

	public InvalidArgumentException(String message) {
		super(message);
	}

	public InvalidArgumentException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public String getHttpErrorCode() {
		return "422";
	}

}
