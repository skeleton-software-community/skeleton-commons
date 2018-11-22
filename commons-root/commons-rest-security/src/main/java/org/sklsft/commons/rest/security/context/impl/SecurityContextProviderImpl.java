package org.sklsft.commons.rest.security.context.impl;

import org.sklsft.commons.rest.security.context.SecurityContextHolder;
import org.sklsft.commons.rest.security.context.SecurityContextProvider;
import org.sklsft.commons.rest.security.credentials.extractor.SecurityCredentialsExtractor;
import org.sklsft.commons.rest.security.credentials.validator.SecurityCredentialsValidator;
import org.sklsft.commons.rest.security.exception.TokenNotFoundException;
import org.sklsft.commons.rest.security.tokens.encoder.TokenEncoder;
import org.sklsft.commons.rest.security.tokens.verification.TokenVerifier;

/**
 * Implementation of {@link SecurityContextProvider} based on the use of :
 * <li>a {@link SecurityContextHolder} which is based on {@link ThreadLocal}
 * <li>a {@link TokenEncoder}
 * <li>a {@link TokenVerifier}
 * <li>a {@link SecurityCredentialsExtractor}
 * <li>a {@link SecurityCredentialsValidator}
 * 
 * @author Nicolas Thibault, Abdessalam El Jai
 */
public class SecurityContextProviderImpl<T, C> implements SecurityContextProvider {

	private TokenEncoder<T> tokenEncoder;
	private TokenVerifier<T> tokenVerifier;
	private SecurityCredentialsExtractor<T, C> credentialsExtractor;
	private SecurityCredentialsValidator<C> credentialsValidator;
	
	
	public SecurityContextProviderImpl(TokenEncoder<T> tokenEncoder, TokenVerifier<T> tokenVerifier,
			SecurityCredentialsExtractor<T, C> credentialsExtractor,
			SecurityCredentialsValidator<C> credentialsValidator) {
		super();
		this.tokenEncoder = tokenEncoder;
		this.tokenVerifier = tokenVerifier;
		this.credentialsExtractor = credentialsExtractor;
		this.credentialsValidator = credentialsValidator;
	}


	@Override
	public void provideSecurityContext(String token) {		
		if (token == null) {
			throw new TokenNotFoundException("token.notFound");
		}
		T tokenObject = tokenEncoder.decode(token);
		tokenVerifier.verifyToken(tokenObject);
		C credentials = credentialsExtractor.extractCredentials(tokenObject);
		credentialsValidator.validateCredentials(credentials);
		SecurityContextHolder.bindCredentials(credentials);
	}
}
