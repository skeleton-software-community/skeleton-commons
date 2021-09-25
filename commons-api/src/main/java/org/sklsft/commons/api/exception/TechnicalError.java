package org.sklsft.commons.api.exception;


/**
 * Exception thrown when no cause can be invoked else a technical cause or a bug
 * 
 * @author Nicolas Thibault
 *
 */
public class TechnicalError extends ApplicationException {

	private static final long serialVersionUID = 1L;
	
	public static final String ERROR_UNKNOWN = "error.unknown";
	
	public TechnicalError(){
		super();
	}
	
	public TechnicalError(String message) {
		super(message);
	}

	public TechnicalError(String message, Throwable cause) {
		super(message, cause);
	}
}