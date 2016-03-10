package org.sklsft.commons.mvc.ajax;

import org.sklsft.commons.api.exception.ApplicationException;
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
	
	private static final Logger logger = LoggerFactory.getLogger(AjaxMethodAspect.class);
	
	private MessageHandler messageHandler;
	
	public void setMessageHandler(MessageHandler messageHandler) {
		this.messageHandler = messageHandler;
	}

	public void executeAjaxMethod(String value, AjaxMethodTemplate template) {
		try {
			logger.info(value);
			Object result = template.execute();
			messageHandler.displayInfo(value + ".success");
			logger.info("completed");
			template.redirectOnComplete(result);
		} catch (ApplicationException e) {
			messageHandler.displayError(e.getMessage());
			logger.error("failed : " + e.getClass().getSimpleName() + " - " + e.getMessage());
		} catch (Exception e) {
			messageHandler.displayError(value + ".failure");
			logger.error("failed : " + e.getClass().getSimpleName() + " - " + e.getMessage());
		}
	}
}
