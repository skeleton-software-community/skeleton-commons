package org.sklsft.commons.log;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.sklsft.commons.api.exception.ApplicationException;
import org.sklsft.commons.crypto.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ErrorLogger {
	
	private static final Logger logger = LoggerFactory.getLogger("ERROR_LOG");
	private static final Logger classLogger = LoggerFactory.getLogger(ErrorLogger.class);
	
	private Serializer serializer;
	
	public ErrorLogger(Serializer serializer) {
		super();
		this.serializer = serializer;
	}
	
	/**
	 * Used to log an error
	 */
	public void logApplicationException(ApplicationException e) {
		
		ErrorLogMessage errorLogMessage = new ErrorLogMessage();
		errorLogMessage.setErrorStatus(e.getHttpErrorCode());
		errorLogMessage.setErrorLabel(e.getMessage());
		
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		e.printStackTrace(printWriter);
		
		errorLogMessage.setErrorTrace(stringWriter.toString());
		
		String serialized;
		try {
			serialized = serializer.serialize(errorLogMessage);
			logger.error(serialized);
		} catch (Exception ex) {
			classLogger.error("failed to log application exception : " + ex.getMessage(),ex);
		}
	}
	
	
	public void logException(Exception e) {
		
		ErrorLogMessage errorLogMessage = new ErrorLogMessage();
		errorLogMessage.setErrorStatus("500");
		errorLogMessage.setErrorLabel(e.getMessage());
		
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		e.printStackTrace(printWriter);
		
		errorLogMessage.setErrorTrace(stringWriter.toString());
		
		String serialized;
		try {
			serialized = serializer.serialize(errorLogMessage);
			logger.error(serialized);
		} catch (Exception ex) {
			classLogger.error("failed to log exception : " + ex.getMessage(),ex);
		}
	}
}
