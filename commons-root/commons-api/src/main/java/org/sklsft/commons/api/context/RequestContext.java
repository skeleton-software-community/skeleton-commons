package org.sklsft.commons.api.context;

import java.io.Serializable;

/**
 * When invoking a transaction through one interface or via a batch, 
 * this context will go along with the transaction and give informations about it :
 * <li>An identifier of the transaction
 * <li>A correlation id, to associate this transaction to others
 * <li>A {link {@link RequestChannels} channel to identify through which kind of interface the transaction was triggered
 * 
 * @author Nicolas Thibault
 *
 */
public class RequestContext implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	/*
	 * constructor
	 */
	public RequestContext() {
		super();
	}

	public RequestContext(String transactionId, String correlationId, RequestChannels channel) {
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
	private RequestChannels channel;

	
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

	public RequestChannels getChannel() {
		return channel;
	}
	public void setChannel(RequestChannels channel) {
		this.channel = channel;
	}
}
