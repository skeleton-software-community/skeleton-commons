package org.sklsft.commons.mvc.messages;

import javax.faces.application.FacesMessage.Severity;

import org.primefaces.context.RequestContext;


/**
 * Simple implementation of a {@link MessageHandler} for Primefaces
 * <br>A Callback is required to be correctly handled in views
 * 
 * @author Nicolas Thibault
 *
 */
public class PrimefacesMessageHandler extends JsfMessageHandler {
	
	@Override
	protected void displayMessage(String message, Severity severity) {
		
		RequestContext.getCurrentInstance().addCallbackParam("maximumSeverity",severity);
		
		super.displayMessage(message, severity);
	}
}
