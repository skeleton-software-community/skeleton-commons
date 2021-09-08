package org.sklsft.commons.mvc.loading;

public class PageLoadExceptionHandlerDefaultJsfImpl extends PageLoadExceptionHandler {

	@Override
	protected String getMissingResourceUrl() {
		return "/errors/404.jsf?faces-redirect=true";
	}

	@Override
	protected String getAccessDeniedUrl() {
		return "/errors/403.jsf?faces-redirect=true";
	}

	@Override
	protected String getExceptionUrl() {
		return "/errors/500.jsf?faces-redirect=true";
	}

	
}
