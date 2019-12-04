package org.sklsft.commons.api.context;

import java.io.Serializable;

public class RequestContext implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	/*
	 * constructor
	 */
	public RequestContext(String requestId, String channel) {
		super();
		this.requestId = requestId;
		this.channel = channel;
	}
	
	
	/*
	 * properties
	 */
	private String requestId;
	private String channel;

	
	/*
	 * getters and setters
	 */
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}	
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
}
