package org.sklsft.commons.log;

import java.io.Serializable;

public class InterfaceCallLogMessage implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/*
	 * properties
	 */
	private TransactionStage transactionStage;
	private String interfaceName;
	private String interfaceChannel;
	private Object sentPayload;
	private Object receivedPayload;
	private Long responseTimeMillis;
	private String responseStatus;
	private String responseLabel;
	
	
	/*
	 * constructor
	 */
	
	
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
	public String getInterfaceChannel() {
		return interfaceChannel;
	}
	public void setInterfaceChannel(String interfaceChannel) {
		this.interfaceChannel = interfaceChannel;
	}
	public Object getSentPayload() {
		return sentPayload;
	}
	public void setSentPayload(Object sentPayload) {
		this.sentPayload = sentPayload;
	}
	public Object getReceivedPayload() {
		return receivedPayload;
	}
	public void setReceivedPayload(Object receivedPayload) {
		this.receivedPayload = receivedPayload;
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
