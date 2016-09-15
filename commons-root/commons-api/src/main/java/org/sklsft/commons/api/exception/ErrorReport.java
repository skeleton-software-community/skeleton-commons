package org.sklsft.commons.api.exception;

import java.io.Serializable;

/**
 * A class used to serialize an {@link ApplicationException} in json
 *
 */
public class ErrorReport implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/*
	 * properties
	 */
	private String exceptionClassName;
	private String message;
	private String detailsClassName;
	private String details;
	
	
	/*
	 * getters and setters
	 */
	public String getExceptionClassName() {
		return exceptionClassName;
	}
	public void setExceptionClassName(String exceptionClassName) {
		this.exceptionClassName = exceptionClassName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDetailsClassName() {
		return detailsClassName;
	}
	public void setDetailsClassName(String detailsClassName) {
		this.detailsClassName = detailsClassName;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
}
