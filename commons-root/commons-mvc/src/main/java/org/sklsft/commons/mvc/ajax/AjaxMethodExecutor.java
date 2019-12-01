package org.sklsft.commons.mvc.ajax;

import org.sklsft.commons.api.exception.ApplicationException;
import org.sklsft.commons.api.exception.TechnicalError;
import org.sklsft.commons.log.AccessLogger;
import org.sklsft.commons.log.ErrorLogger;
import org.sklsft.commons.mvc.messages.MessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * when {@link AjaxMethodAspect} is impossible due to redirection<br>
 * It is possible to templatize your controller calls whith a {@link AjaxMethodTemplate}<br>
 * the treatment is similar to what is done in the aspect but we handle a redirection that can depend on the result of the execution
 * 
 * @author Nicolas Thibault
 *
 */
public class AjaxMethodExecutor {
	
	private MessageHandler messageHandler;
	private AccessLogger accessLogger;
	private ErrorLogger errorLogger;
	
	public void setMessageHandler(MessageHandler messageHandler) {
		this.messageHandler = messageHandler;
	}
	public void setAccessLogger(AccessLogger accessLogger) {
		this.accessLogger = accessLogger;
	}
	public void setErrorLogger(ErrorLogger errorLogger) {
		this.errorLogger = errorLogger;
	}

	public void executeAjaxMethod(String value, AjaxMethodTemplate template) {
		long elapsedTime;
		long start = System.currentTimeMillis();
		try {
			accessLogger.logRequest(value, "calling ajax method " + value, null);			
			Object result = template.execute();
			elapsedTime = System.currentTimeMillis() - start;
			accessLogger.logResponse(value, "ajax method " + value + " completed", null, elapsedTime, "200", "OK");
			messageHandler.displayInfo(value + ".success");
			template.redirectOnComplete(result);
		} catch (ApplicationException e) {
			elapsedTime = System.currentTimeMillis() - start;
			accessLogger.logResponse(value, "ajax method " + value + " completed with error", null, elapsedTime, e.getHttpErrorCode(), e.getMessage());
			errorLogger.logException(e);
			messageHandler.displayError(e.getMessage());
		} catch (Exception e) {
			elapsedTime = System.currentTimeMillis() - start;
			accessLogger.logResponse(value, "ajax method " + value + " completed with error", null, elapsedTime, "500", e.getMessage());
			errorLogger.logException(e);
			messageHandler.displayError(value + ".failure");
		} catch (Throwable e) {
			throw new TechnicalError(TechnicalError.ERROR_UNKNOWN);
		}
	}
}
