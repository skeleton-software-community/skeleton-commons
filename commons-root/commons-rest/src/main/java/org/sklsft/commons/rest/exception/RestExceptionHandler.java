package org.sklsft.commons.rest.exception;

import java.io.IOException;

import org.sklsft.commons.api.exception.ApplicationException;
import org.sklsft.commons.api.exception.ErrorReport;
import org.sklsft.commons.api.exception.TechnicalError;
import org.sklsft.commons.crypto.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.JsonViewResponseBodyAdvice;


/**
 * How to handle exceptions :
 * <li>If it is an {@link ApplicationException}, it will be serialized to an {@link ErrorReport}
 * <li>Else, an unknown {@link TechnicalError} will replace it for serialization.
 * 
 * @author Nicolas Thibault
 *
 */
@ControllerAdvice
public class RestExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);
	
	private Serializer serializer;
	
	public void setSerializer(Serializer serializer) {
		this.serializer = serializer;
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ApplicationException.class)
	public @ResponseBody ErrorReport handleApplicationException(ApplicationException e) {
		
		ErrorReport errorReport = new ErrorReport();
		errorReport.setExceptionClassName(e.getClass().getName());
		errorReport.setMessage(e.getMessage());
		
		Object details = e.getDetails();
		
		if (details != null) {
			try {
				errorReport.setDetails(serializer.serialize(details));
				errorReport.setDetailsClassName(details.getClass().getName());
			} catch (IOException ioe) {
				logger.error("failed to handle exception : " + e.getMessage() + " - " + e.getDetails().toString(), ioe);
			}
		}
		
		return errorReport;
	}
	
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public @ResponseBody ErrorReport handleException(Exception e) {
		
		ErrorReport errorReport = new ErrorReport();
		errorReport.setExceptionClassName(TechnicalError.class.getName());
		errorReport.setMessage(ApplicationException.ERROR_UNKNOWN);
		
		return errorReport;
	}
}
