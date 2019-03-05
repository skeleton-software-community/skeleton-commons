package org.sklsft.commons.api.exception;

/**
 * This RuntimeException is used to be handled by several aspects<br>
 * It overrides the message to enable the serialization/deserialization using setters as required in json serialization for instance
 * 
 * @author Nicolas Thibault
 *
 */
public abstract class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	private Object details;
	

	public ApplicationException(){
		super();
	}
	
	public ApplicationException(String message) {
		super(message);
		this.message = message;

	}
	
	public ApplicationException(String message, Object details) {
		super(message);
		this.message = message;
		this.details = details;
	}

	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
		this.message = message;
	}
	
	public ApplicationException(String message, Throwable cause, Object details) {
		super(message, cause);
		this.message = message;
		this.details = details;
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public Object getDetails() {
		return details;
	}

	public void setDetails(Object details) {
		this.details = details;
	}
}