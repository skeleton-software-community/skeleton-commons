package org.sklsft.commons.text.serialization.exceptions;

/**
 * This RuntimeException is sent after catching a non runtime exception during serialization/deserialization.
 * 
 * @author Nicolas Thibault
 *
 */
public class SerializationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public SerializationException(){
		super();
	}
	
	public SerializationException(String message) {
		super(message);
	}

	public SerializationException(String message, Throwable cause) {
		super(message, cause);
	}
	
}