package org.sklsft.commons.api.context;

import java.io.Serializable;

public class RequestContext implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	public RequestContext(String requestId) {
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
