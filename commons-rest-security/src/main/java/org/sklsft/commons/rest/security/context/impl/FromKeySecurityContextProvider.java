package org.sklsft.commons.rest.security.context.impl;

import org.sklsft.commons.rest.security.credentials.validator.SecurityCredentialsValidator;

/**
 * 
 * 
 * @author Nicolas Thibault
 */
public abstract class FromKeySecurityContextProvider<C> extends BasicSecurityContextProvider<C> {

	private SecurityCredentialsValidator<C> credentialsValidator;
	
	
	public FromKeySecurityContextProvider(SecurityCredentialsValidator<C> credentialsValidator) {
		super();
		this.credentialsValidator = credentialsValidator;
	}
	
	@Override
	protected C getValidCredentials(String token) {		
		C credentials = get(token);
		credentialsValidator.validateCredentials(credentials);
		return credentials;
	}

	protected abstract C get(String token);
}
