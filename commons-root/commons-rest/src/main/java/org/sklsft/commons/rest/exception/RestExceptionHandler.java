package org.sklsft.commons.rest.exception;

import org.sklsft.commons.api.exception.ApplicationException;
import org.sklsft.commons.api.exception.ErrorReport;
import org.sklsft.commons.api.exception.TechnicalError;
import org.sklsft.commons.api.exception.rights.AccessDeniedException;
import org.sklsft.commons.api.exception.rights.OperationDeniedException;
import org.sklsft.commons.api.exception.validation.InvalidArgumentException;
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

	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	@ExceptionHandler(AccessDeniedException.class)
	public @ResponseBody ErrorReport handleApplicationException(AccessDeniedException e) {

		ErrorReport errorReport = new ErrorReport();
		errorReport.setExceptionClassName(e.getClass().getName());
		errorReport.setMessage(e.getMessage());
		errorReport.setDetails(e.getDetails());

		return errorReport;
	}
	
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	@ExceptionHandler(OperationDeniedException.class)
	public @ResponseBody ErrorReport handleApplicationException(OperationDeniedException e) {

		ErrorReport errorReport = new ErrorReport();
		errorReport.setExceptionClassName(e.getClass().getName());
		errorReport.setMessage(e.getMessage());
		errorReport.setDetails(e.getDetails());

		return errorReport;
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ApplicationException.class)
	public @ResponseBody ErrorReport handleApplicationException(ApplicationException e) {

		ErrorReport errorReport = new ErrorReport();
		errorReport.setExceptionClassName(e.getClass().getName());
		errorReport.setMessage(e.getMessage());
		errorReport.setDetails(e.getDetails());

		return errorReport;
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public @ResponseBody ErrorReport handleException(Exception e) {

		ErrorReport errorReport = new ErrorReport();
		errorReport.setExceptionClassName(TechnicalError.class.getName());
		errorReport.setMessage(TechnicalError.ERROR_UNKNOWN);

		return errorReport;
	}
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public @ResponseBody ErrorReport handleException(MethodArgumentNotValidException e) {

		ErrorReport errorReport = new ErrorReport();
		errorReport.setExceptionClassName(InvalidArgumentException.class.getName());
		errorReport.setMessage(InvalidArgumentException.INVALID_ARGUMENTS);

		return errorReport;
	}
}
