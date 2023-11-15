package org.sklsft.commons.rest.security.context.impl;

import org.sklsft.commons.rest.security.credentials.validator.SecurityContextValidator;
import org.sklsft.commons.rest.security.tokens.encoder.TokenDecoder;

/**
 * 
 * @author Nicolas Thibault
 */
public class FromCryptedTokenSecurityContextProvider<T> extends BasicSecurityContextProvider<T> {

	private TokenDecoder<T> tokenDecoder;
	private SecurityContextValidator<T> contextValidator;
	
	
	public FromCryptedTokenSecurityContextProvider(TokenDecoder<T> tokenDecoder, SecurityContextValidator<T> contextValidator) {
		super();
		this.tokenDecoder = tokenDecoder;
		this.contextValidator = contextValidator;
	}


	@Override
	protected T getValidContext(String token) {		
		T context = tokenDecoder.decode(token);
		contextValidator.validateContext(context);
		return context;
	}
}
