package org.sklsft.commons.rest.exception;

import org.sklsft.commons.api.exception.ApplicationException;
import org.sklsft.commons.api.exception.ErrorReport;
import org.sklsft.commons.api.exception.TechnicalError;
import org.sklsft.commons.rest.aspect.LoggingAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


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
	
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ApplicationException.class)
	public @ResponseBody ErrorReport handleApplicationException(ApplicationException e) {
		
		logger.error("exception thrown : " + e.getMessage(), e);
		
		ErrorReport errorReport = new ErrorReport();
		errorReport.setExceptionClassName(e.getClass().getName());
		errorReport.setMessage(e.getMessage());
		
		return errorReport;
	}
	
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public @ResponseBody ErrorReport handleException(Exception e) {
		
		logger.error("exception thrown : " + e.getMessage(), e);
		
		ErrorReport errorReport = new ErrorReport();
		errorReport.setExceptionClassName(TechnicalError.class.getName());
		errorReport.setMessage(ApplicationException.ERROR_UNKNOWN);
		
		return errorReport;
	}
}
