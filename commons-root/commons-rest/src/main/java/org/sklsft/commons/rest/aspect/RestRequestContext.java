package org.sklsft.commons.rest.aspect;

import java.io.Serializable;

public class RestRequestContext implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	public RestRequestContext(String requestId) {
		super();
		this.requestId = requestId;
	}
	
	
	/*
	 * properties
	 */
	private String requestId;

	
	/*
	 * getters and setters
	 */
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
}
