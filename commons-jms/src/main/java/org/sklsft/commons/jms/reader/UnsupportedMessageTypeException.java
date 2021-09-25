package org.sklsft.commons.jms.reader;

import org.sklsft.commons.api.exception.TechnicalError;

public class UnsupportedMessageTypeException extends TechnicalError {

	private static final long serialVersionUID = 1L;
	
	
	public UnsupportedMessageTypeException(String message) {
		super(message);
	}
}
