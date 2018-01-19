package org.sklsft.commons.rest.security.credentials.impl;

import org.sklsft.commons.rest.security.credentials.SecurityCredentialsEncoder;
import org.sklsft.commons.rest.security.credentials.SecurityCredentialsRetriever;
import org.sklsft.commons.rest.security.credentials.SecurityCredentialsValidator;

/**
 *
 *
 * @author Nicolas Thibault, Abdessalam El Jai, Alexandre Rupp
 */
public abstract class FromMapCredentialsRetriever<T> implements SecurityCredentialsRetriever<T> {

	private SecurityCredentialsValidator<T> credentialsValidator;
	
	
	public FromMapCredentialsRetriever(SecurityCredentialsValidator<T> credentialsValidator) {
		super();
		this.credentialsValidator = credentialsValidator;
	}



	@Override
	public T retrieveCredentials(String token) {
		
		T credentials = get(token);
		credentialsValidator.validateCredentials(credentials);		
		return credentials;
	}
	
	protected abstract T get(String token);
}
