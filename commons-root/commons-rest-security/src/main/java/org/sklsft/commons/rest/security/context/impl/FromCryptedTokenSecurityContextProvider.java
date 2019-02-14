package org.sklsft.commons.rest.security.context.impl;

import org.sklsft.commons.rest.security.credentials.validator.SecurityCredentialsValidator;
import org.sklsft.commons.rest.security.tokens.encoder.TokenEncoder;

/**
 * 
 * @author Nicolas Thibault
 */
public class FromCryptedTokenSecurityContextProvider<T> extends BasicSecurityContextProvider<T> {

	private TokenEncoder<T> tokenEncoder;
	private SecurityCredentialsValidator<T> credentialsValidator;
	
	
	public FromCryptedTokenSecurityContextProvider(TokenEncoder<T> tokenEncoder, SecurityCredentialsValidator<T> credentialsValidator) {
		super();
		this.tokenEncoder = tokenEncoder;
		this.credentialsValidator = credentialsValidator;
	}


	@Override
	protected T getValidCredentials(String token) {		
		T tokenObject = tokenEncoder.decode(token);
		credentialsValidator.validateCredentials(tokenObject);
		return tokenObject;
	}
}
