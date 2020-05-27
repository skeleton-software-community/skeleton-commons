package org.sklsft.commons.log;

import java.io.Serializable;

public class AccessLogMessage implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/*
	 * properties
	 */
	private TransactionStage transactionStage;
	private String transactionType;
	private Object requestBody;
	private Object responseBody;
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
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public Object getRequestBody() {
		return requestBody;
	}
	public void setRequestBody(Object requestBody) {
		this.requestBody = requestBody;
	}
	public Object getResponseBody() {
		return responseBody;
	}
	public void setResponseBody(Object responseBody) {
		this.responseBody = responseBody;
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
