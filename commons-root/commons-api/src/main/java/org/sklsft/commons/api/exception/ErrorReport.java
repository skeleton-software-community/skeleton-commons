package org.sklsft.commons.api.exception;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

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
	@JsonTypeInfo(use = Id.CLASS, include = As.PROPERTY, property = "class")
	private Object details;
	private String transactionId;

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
	public Object getDetails() {
		return details;
	}
	public void setDetails(Object details) {
		this.details = details;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

}
