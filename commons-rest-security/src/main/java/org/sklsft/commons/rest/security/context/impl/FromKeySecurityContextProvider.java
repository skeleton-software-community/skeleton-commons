package org.sklsft.commons.rest.security.context.impl;

import org.sklsft.commons.rest.security.credentials.validator.SecurityContextValidator;

/**
 * 
 * 
 * @author Nicolas Thibault
 */
public abstract class FromKeySecurityContextProvider<C> extends BasicSecurityContextProvider<C> {

	private SecurityContextValidator<C> contextValidator;
	
	
	public FromKeySecurityContextProvider(SecurityContextValidator<C> contextValidator) {
		super();
		this.contextValidator = contextValidator;
	}
	
	@Override
	protected C getValidContext(String token) {		
		C credentials = get(token);
		contextValidator.validateContext(credentials);
		return credentials;
	}

	protected abstract C get(String token);
}
