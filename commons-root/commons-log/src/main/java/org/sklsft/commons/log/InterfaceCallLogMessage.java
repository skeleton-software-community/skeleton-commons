package org.sklsft.commons.log;

import java.io.Serializable;

import org.sklsft.commons.api.context.RequestChannels;

public class InterfaceCallLogMessage implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/*
	 * properties
	 */
	private TransactionStage transactionStage;
	private String interfaceName;
	private RequestChannels interfaceChannel;
	private Object sentBody;
	private Object receivedBody;
	private Long responseTimeMillis;
	private String responseStatus;
	private String responseLabel;
	
	
	
	/*
	 * getters and setters
	 */
	public TransactionStage getTransactionStage() {
		return transactionStage;
	}
	public void setTransactionStage(TransactionStage transactionStage) {
		this.transactionStage = transactionStage;
	}
	public String getInterfaceName() {
		return interfaceName;
	}
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}
	public RequestChannels getInterfaceChannel() {
		return interfaceChannel;
	}
	public void setInterfaceChannel(RequestChannels interfaceChannel) {
		this.interfaceChannel = interfaceChannel;
	}
	public Object getSentBody() {
		return sentBody;
	}
	public void setSentBody(Object sentBody) {
		this.sentBody = sentBody;
	}
	public Object getReceivedBody() {
		return receivedBody;
	}
	public void setReceivedBody(Object receivedBody) {
		this.receivedBody = receivedBody;
	}
	public Long getResponseTimeMillis() {
		return responseTimeMillis;
	}
	public void setResponseTimeMillis(Long responseTimeMillis) {
		this.responseTimeMillis = responseTimeMillis;
	}
	public String getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}
	public String getResponseLabel() {
		return responseLabel;
	}
	public void setResponseLabel(String responseLabel) {
		this.responseLabel = responseLabel;
	}
}
