package org.sklsft.commons.log;

import java.io.Serializable;

public class ErrorLogMessage implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/*
	 * properties
	 */
	private String errorStatus;
	private String errorLabel;
	private String errorTrace;
	
	
	/*
	 * getters and setters
	 */
	public String getErrorStatus() {
		return errorStatus;
	}
	public void setErrorStatus(String errorStatus) {
		this.errorStatus = errorStatus;
	}
	public String getErrorLabel() {
		return errorLabel;
	}
	public void setErrorLabel(String errorLabel) {
		this.errorLabel = errorLabel;
	}
	public String getErrorTrace() {
		return errorTrace;
	}
	public void setErrorTrace(String errorTrace) {
		this.errorTrace = errorTrace;
	}
}
