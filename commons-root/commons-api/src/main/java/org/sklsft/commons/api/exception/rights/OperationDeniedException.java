package org.sklsft.commons.api.exception.rights;

import org.sklsft.commons.api.exception.ApplicationException;

/**
 * Exception thrown by rights manager if a user is rejected when proceeding an operation
 * 
 * @author Nicolas Thibault
 *
 */
public class OperationDeniedException extends ApplicationException {

	private static final long serialVersionUID = 1L;

	
	public OperationDeniedException(){
		super();
	}
	
	public OperationDeniedException(String message) {
		super(message);

	}

	public OperationDeniedException(String message, Throwable cause) {
		super(message, cause);
	}
	
	
	@Override
	public String getHttpErrorCode() {
		return "500";
	}
}
