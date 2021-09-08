package org.sklsft.commons.mvc.loading;

import org.sklsft.commons.mvc.redirection.RedirectionHandler;


/**
 * Defines what happens when an exception is thrown during a page load<br>
 * 
 * @author Nicolas Thibault
 *
 */
public abstract class PageLoadExceptionHandler {

	/**
	 * to be injected
	 */
	private RedirectionHandler redirectionHandler;

	public void setRedirectionHandler(RedirectionHandler redirectionHandler) {
		this.redirectionHandler = redirectionHandler;
	}

	/**
	 * redirects to dedicated page for missing resource
	 */
	public void redirectOnMissingResource() {
		redirectionHandler.redirect(getMissingResourceUrl());

	}

	protected abstract String getMissingResourceUrl();

	/**
	 * redirects to dedicated page for access denied
	 */
	public void redirectOnAccessDenied() {
		redirectionHandler.redirect(getAccessDeniedUrl());

	}

	protected abstract String getAccessDeniedUrl();

	/**
	 * redirects to dedicated page for other exceptions
	 */
	public void redirectOnException() {
		redirectionHandler.redirect(getExceptionUrl());
	}

	protected abstract String getExceptionUrl();
}
