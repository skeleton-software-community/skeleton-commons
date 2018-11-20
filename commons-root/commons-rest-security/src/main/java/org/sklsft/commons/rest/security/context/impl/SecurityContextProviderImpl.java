package org.sklsft.commons.rest.security.context.impl;

import org.sklsft.commons.rest.security.context.SecurityContextHolder;
import org.sklsft.commons.rest.security.context.SecurityContextProvider;
import org.sklsft.commons.rest.security.credentials.retriever.SecurityCredentialsRetriever;
import org.sklsft.commons.rest.security.credentials.validator.SecurityCredentialsValidator;
import org.sklsft.commons.rest.security.exception.TokenNotFoundException;
import org.sklsft.commons.rest.security.tokens.verification.TokenVerifier;

/**
 * Implementation of {@link SecurityContextProvider} based on the use of :
 * <li>a {@link SecurityContextHolder} which is based on {@link ThreadLocal}
 * <li>a {@link SecurityCredentialsRetriever}
 * <li>a {@link SecurityCredentialsValidator}
 * 
 * @author Nicolas Thibault, Abdessalam El Jai
 */
public class SecurityContextProviderImpl<U> implements SecurityContextProvider {

	private TokenVerifier tokenVerifier;
	private SecurityCredentialsRetriever<U> credentialsRetriever;
	private SecurityCredentialsValidator<U> credentialsValidator;
	

	public SecurityContextProviderImpl(TokenVerifier tokenVerifier, SecurityCredentialsRetriever<U> credentialsRetriever, SecurityCredentialsValidator<U> credentialsValidator) {
		super();
		this.tokenVerifier = tokenVerifier;
		this.credentialsRetriever = credentialsRetriever;
		this.credentialsValidator = credentialsValidator;
	}
	
	
	@Override
	public void provideSecurityContext(String token) {
		
		if (token == null) {
			throw new TokenNotFoundException("token.notFound");
		}
		tokenVerifier.verifyToken(token);
		U credentials = credentialsRetriever.retrieveCredentials(token);
		credentialsValidator.validateCredentials(credentials);
		SecurityContextHolder.bindCredentials(credentials);
	}
}
