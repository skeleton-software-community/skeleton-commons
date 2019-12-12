package org.sklsft.commons.api.context;

import java.io.Serializable;

public class RequestContext implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	/*
	 * constructor
	 */
	public RequestContext(String transactionId, String correlationId, String channel) {
		super();
		this.transactionId = transactionId;
		this.correlationId = correlationId;
		this.channel = channel;
	}
	
	
	/*
	 * properties
	 */
	private String transactionId;
	private String correlationId;
	private String channel;

	
	/*
	 * getters and setters
	 */	
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getCorrelationId() {
		return correlationId;
	}
	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
}
