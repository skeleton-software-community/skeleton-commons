package org.sklsft.commons.log;

import java.io.IOException;

import org.sklsft.commons.api.exception.TechnicalError;
import org.sklsft.commons.crypto.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccessLogger {
	
	private static final Logger logger = LoggerFactory.getLogger(AccessLogger.class);
	
	private Serializer serializer;
	
	public AccessLogger(Serializer serializer) {
		super();
		this.serializer = serializer;
	}
	

	public void logRequest(String transactionType, String message, Object requestPayload) {
		AccessLogMessage accessMessage = new AccessLogMessage();
		accessMessage.setTransactionStage(TransactionStage.REQUEST);
		accessMessage.setTransactionType(transactionType);
		accessMessage.setMessage(message);
		accessMessage.setRequestPayload(requestPayload);
		String serialized;
		try {
			serialized = serializer.serialize(accessMessage);
			logger.info(serialized);
		} catch (IOException e) {
			throw new TechnicalError("failed to log request : " + e.getMessage());
		}		
	}
	
	public void logResponse(String transactionType, String message, Object responsePayload, Long responseTimeMillis, String responseStatus, String responseLabel) {
		AccessLogMessage accessMessage = new AccessLogMessage();
		accessMessage.setTransactionStage(TransactionStage.RESPONSE);
		accessMessage.setTransactionType(transactionType);
		accessMessage.setMessage(message);		
		accessMessage.setResponsePayload(responsePayload);
		accessMessage.setResponseTimeMillis(responseTimeMillis);
		accessMessage.setResponseStatus(responseStatus);
		accessMessage.setResponseLabel(responseLabel);
		String serialized;
		try {
			serialized = serializer.serialize(accessMessage);
			logger.info(serialized);
		} catch (IOException e) {
			throw new TechnicalError("failed to log response : " + e.getMessage());
		}
	}
}
