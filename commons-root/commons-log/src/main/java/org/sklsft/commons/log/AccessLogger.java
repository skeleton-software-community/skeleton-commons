package org.sklsft.commons.log;

import org.sklsft.commons.api.context.RequestChannels;
import org.sklsft.commons.text.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccessLogger {
	
	private static final Logger logger = LoggerFactory.getLogger("ACCESS_LOG");
	private static final Logger classLogger = LoggerFactory.getLogger(AccessLogger.class);
	
	private Serializer serializer;
	
	public AccessLogger(Serializer serializer) {
		super();
		this.serializer = serializer;
	}
	
	/**
	 * Used to log a request received as a backend
	 */
	public void logRequest(String transactionType, Object requestBody) {
		AccessLogMessage accessMessage = new AccessLogMessage();
		accessMessage.setTransactionStage(TransactionStage.REQUEST);
		accessMessage.setTransactionType(transactionType);
		accessMessage.setRequestBody(requestBody);
		String serialized;
		try {
			serialized = serializer.serialize(accessMessage);
			logger.info(serialized);
		} catch (Exception e) {
			classLogger.error("failed to log request : " + e.getMessage(),e);
		}
	}
	
	
	/**
	 * Used to log a response sent as a backend
	 */
	public void logResponse(String transactionType, Object responseBody, Long responseTimeMillis, String responseStatus, String responseLabel) {
		AccessLogMessage accessMessage = new AccessLogMessage();
		accessMessage.setTransactionStage(TransactionStage.RESPONSE);
		accessMessage.setTransactionType(transactionType);	
		accessMessage.setResponseBody(responseBody);
		accessMessage.setResponseTimeMillis(responseTimeMillis);
		accessMessage.setResponseStatus(responseStatus);
		accessMessage.setResponseLabel(responseLabel);
		String serialized;
		try {
			serialized = serializer.serialize(accessMessage);
			logger.info(serialized);
		} catch (Exception e) {
			classLogger.error("failed to log response : " + e.getMessage(),e);
		}
	}
	
	
	/**
	 * Used to log a request sent as a client
	 */
	public void logInterfaceCall(String interfaceName, RequestChannels interfaceChannel, Object sentBody) {
		InterfaceCallLogMessage accessMessage = new InterfaceCallLogMessage();
		accessMessage.setTransactionStage(TransactionStage.INTERFACE_CALL);
		accessMessage.setInterfaceName(interfaceName);
		accessMessage.setInterfaceChannel(interfaceChannel);
		accessMessage.setSentBody(sentBody);
		String serialized;
		try {
			serialized = serializer.serialize(accessMessage);
			logger.info(serialized);
		} catch (Exception e) {
			classLogger.error("failed to log interface call : " + e.getMessage(),e);
		}
	}
	
	
	/**
	 * Used to log a response received as a client
	 */
	public void logInterfaceAnswer(String interfaceName, RequestChannels interfaceChannel, Object receivedBody, Long responseTimeMillis, String responseStatus, String responseLabel) {
		InterfaceCallLogMessage accessMessage = new InterfaceCallLogMessage();
		accessMessage.setTransactionStage(TransactionStage.INTERFACE_ANSWER);
		accessMessage.setInterfaceName(interfaceName);
		accessMessage.setInterfaceChannel(interfaceChannel);
		accessMessage.setReceivedBody(receivedBody);
		accessMessage.setResponseTimeMillis(responseTimeMillis);
		accessMessage.setResponseStatus(responseStatus);
		accessMessage.setResponseLabel(responseLabel);
		String serialized;
		try {
			serialized = serializer.serialize(accessMessage);
			logger.info(serialized);
		} catch (Exception e) {
			classLogger.error("failed to log interface answer : " + e.getMessage(),e);
		}
	}
}
