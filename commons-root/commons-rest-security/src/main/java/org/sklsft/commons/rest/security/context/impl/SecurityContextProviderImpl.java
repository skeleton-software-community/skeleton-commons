package org.sklsft.commons.rest.security.context.impl;

import org.sklsft.commons.rest.security.context.SecurityContextHolder;
import org.sklsft.commons.rest.security.context.SecurityContextProvider;
import org.sklsft.commons.rest.security.credentials.retriever.SecurityCredentialsRetriever;
import org.sklsft.commons.rest.security.credentials.validator.SecurityCredentialsValidator;
import org.sklsft.commons.rest.security.exception.TokenNotFoundException;

/**
 * Implementation of {@link SecurityContextProvider} based on the use of :
 * <li>a {@link SecurityContextHolder} which is based on {@link ThreadLocal}
 * <li>a {@link SecurityCredentialsRetriever}
 * <li>a {@link SecurityCredentialsValidator}
 * 
 * @author Nicolas Thibault, Abdessalam El Jai
 */
public class SecurityContextProviderImpl<U> implements SecurityContextProvider {

	private SecurityCredentialsRetriever<U> credentialsRetriever;
	private SecurityCredentialsValidator<U> credentialsValidator;
	

	public SecurityContextProviderImpl(SecurityCredentialsRetriever<U> credentialsRetriever, SecurityCredentialsValidator<U> credentialsValidator) {
		super();
		this.credentialsRetriever = credentialsRetriever;
		this.credentialsValidator = credentialsValidator;
	}
	
	
	@Override
	public void provideSecurityContext(String token) {
		
		if (token == null) {
			throw new TokenNotFoundException("token.notFound");
		}

		U credentials = credentialsRetriever.retrieveCredentials(token);
		credentialsValidator.validateCredentials(credentials, token);
		SecurityContextHolder.bindCredentials(credentials);
	}
}
