package org.sklsft.commons.rest.exception;

import org.sklsft.commons.api.exception.ApplicationException;
import org.sklsft.commons.api.exception.ErrorReport;
import org.sklsft.commons.api.exception.TechnicalError;
import org.sklsft.commons.api.exception.repository.ObjectNotFoundException;
import org.sklsft.commons.api.exception.rights.AccessDeniedException;
import org.sklsft.commons.api.exception.validation.InvalidArgumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * How to handle exceptions : <li>If it is an {@link ApplicationException}, it
 * will be serialized to an {@link ErrorReport} <li>Else, an unknown
 * {@link TechnicalError} will replace it for serialization.
 * 
 * @author Nicolas Thibault
 *
 */
@ControllerAdvice
public class RestExceptionHandler {	
	
	private static final Logger classLogger = LoggerFactory.getLogger(RestExceptionHandler.class);
	
	private boolean printErrorStackInRootLogger = true;
	
	public void setPrintErrorStackInRootLogger(boolean printErrorStackInRootLogger) {
		this.printErrorStackInRootLogger = printErrorStackInRootLogger;
	}

	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	@ExceptionHandler(AccessDeniedException.class)
	public @ResponseBody ErrorReport handleApplicationException(AccessDeniedException e) {
		
		if (printErrorStackInRootLogger) classLogger.error(e.getMessage(),e);

		ErrorReport errorReport = new ErrorReport();
		errorReport.setExceptionClassName(e.getClass().getName());
		errorReport.setMessage(e.getMessage());

		return errorReport;
	}
	
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	@ExceptionHandler(org.springframework.security.access.AccessDeniedException.class)
	public @ResponseBody ErrorReport handleApplicationException(org.springframework.security.access.AccessDeniedException e) {
		
		if (printErrorStackInRootLogger) classLogger.error(e.getMessage(),e);

		ErrorReport errorReport = new ErrorReport();
		errorReport.setExceptionClassName(AccessDeniedException.class.getName());
		errorReport.setMessage(AccessDeniedException.ACCESS_DENIED);

		return errorReport;
	}
	
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(ObjectNotFoundException.class)
	public @ResponseBody ErrorReport handleApplicationException(ObjectNotFoundException e) {
		
		if (printErrorStackInRootLogger) classLogger.error(e.getMessage(),e);

		ErrorReport errorReport = new ErrorReport();
		errorReport.setExceptionClassName(e.getClass().getName());
		errorReport.setMessage(e.getMessage());

		return errorReport;
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ApplicationException.class)
	public @ResponseBody ErrorReport handleApplicationException(ApplicationException e) {
		
		if (printErrorStackInRootLogger) classLogger.error(e.getMessage(),e);

		ErrorReport errorReport = new ErrorReport();
		errorReport.setExceptionClassName(e.getClass().getName());
		errorReport.setMessage(e.getMessage());

		return errorReport;
	}
	
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public @ResponseBody ErrorReport handleException(Exception e) {
		
		if (printErrorStackInRootLogger) classLogger.error(e.getMessage(),e);

		ErrorReport errorReport = new ErrorReport();
		errorReport.setExceptionClassName(TechnicalError.class.getName());
		errorReport.setMessage(e.getMessage());

		return errorReport;
	}
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public @ResponseBody ErrorReport handleException(MethodArgumentNotValidException e) {
		
		if (printErrorStackInRootLogger) classLogger.error(e.getMessage(),e);

		ErrorReport errorReport = new ErrorReport();
		errorReport.setExceptionClassName(InvalidArgumentException.class.getName());
		errorReport.setMessage(InvalidArgumentException.INVALID_ARGUMENTS);

		return errorReport;
	}
}
