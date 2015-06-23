package org.sklsft.commons.mvc.redirection;


public interface RedirectionHandler {

	void redirect(String relativeUrl, boolean keepMessages);	
	
	void redirect(String relativeUrl);
	
	void redirectToExterior(String url, boolean keepMessages);
	
	void redirectToExterior(String url);
}
