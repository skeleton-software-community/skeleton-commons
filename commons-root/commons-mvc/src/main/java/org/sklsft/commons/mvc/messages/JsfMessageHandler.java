package org.sklsft.commons.mvc.messages;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import org.sklsft.commons.api.exception.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Simple implementation of a {@link MessageHandler} for JSF
 * 
 * @author Nicolas Thibault
 *
 */
public class JsfMessageHandler implements MessageHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(JsfMessageHandler.class);	
	

	@Override
	public void displayInfo(String message) {
		
		displayMessage(message, FacesMessage.SEVERITY_INFO);
	}
	

	@Override
	public void displayWarning(String message) {
		
		displayMessage(message, FacesMessage.SEVERITY_WARN);
	}
	

	@Override
	public void displayError(String message) {

		displayMessage(message, FacesMessage.SEVERITY_ERROR);
	}
	
	
	protected void displayMessage(String message, Severity severity) {
		
		String translatedMessage = getTranslatedMessage(message);
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, translatedMessage , null));
	}
	
	
	private String getTranslatedMessage(String message) {
		
		if (message == null) {
			message = ApplicationException.ERROR_UNKNOWN;
		}
		
		String translatedMessage = message;
		
		try {
			translatedMessage = ResourceBundle.getBundle("GlobalMessages", FacesContext.getCurrentInstance().getViewRoot().getLocale()).getString(message);
		} catch (MissingResourceException e) {
			logger.warn("No message found for " + message);
		}
		
		return translatedMessage;
	}

}
