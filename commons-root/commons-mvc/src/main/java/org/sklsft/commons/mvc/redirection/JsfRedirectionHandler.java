package org.sklsft.commons.mvc.redirection;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.sklsft.commons.api.exception.ApplicationException;
import org.sklsft.commons.api.exception.TechnicalError;

public class JsfRedirectionHandler implements RedirectionHandler {

	public void redirect(String relativeUrl, boolean keepMessages) {
		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		
		if (keepMessages) {
			externalContext.getFlash().setKeepMessages(true);
		}

		try {
			externalContext.redirect(externalContext.getRequestContextPath() + relativeUrl);
		} catch (IOException e) {
			throw new TechnicalError(ApplicationException.ERROR_UNKNOWN, e) ;
		}
	}
	
	
	public void redirect(String relativeUrl) {
		
		redirect(relativeUrl, true);
	}
	
	
	public void redirectToExterior(String url, boolean keepMessages) {

		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

		if (keepMessages) {
			externalContext.getFlash().setKeepMessages(true);
		}
		
		try {
			externalContext.redirect(url);
		} catch (IOException e) {
			throw new TechnicalError(ApplicationException.ERROR_UNKNOWN, e);
		}

	}
	
	
	public void redirectToExterior(String url) {

		redirectToExterior(url, true);
	}
}
