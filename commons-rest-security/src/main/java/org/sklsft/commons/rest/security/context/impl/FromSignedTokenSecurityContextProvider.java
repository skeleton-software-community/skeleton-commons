package org.sklsft.commons.rest.security.context.impl;

import org.sklsft.commons.rest.security.context.SecurityContextHolder;
import org.sklsft.commons.rest.security.context.SecurityContextProvider;
import org.sklsft.commons.rest.security.credentials.extractor.SecurityContextExtractor;
import org.sklsft.commons.rest.security.credentials.validator.SecurityContextValidator;
import org.sklsft.commons.rest.security.tokens.encoder.TokenDecoder;
import org.sklsft.commons.rest.security.tokens.verification.TokenVerifier;

/**
 * Implementation of {@link SecurityContextProvider} based on the use of :
 * <li>a {@link SecurityContextHolder} which is based on {@link ThreadLocal}
 * <li>a {@link TokenDecoder}
 * <li>a {@link TokenVerifier}
 * <li>a {@link SecurityContextExtractor}
 * <li>a {@link SecurityContextValidator}
 * 
 * @author Nicolas Thibault
 */
public class FromSignedTokenSecurityContextProvider<T, C> extends BasicSecurityContextProvider<C> {

	private TokenDecoder<T> tokenDecoder;
	private TokenVerifier<T> tokenVerifier;
	private SecurityContextExtractor<T, C> contextExtractor;
	private SecurityContextValidator<C> contextValidator;
	
	
	public FromSignedTokenSecurityContextProvider(TokenDecoder<T> tokenDecoder, TokenVerifier<T> tokenVerifier,
			SecurityContextExtractor<T, C> contextExtractor, SecurityContextValidator<C> contextValidator) {
		super();
		this.tokenDecoder = tokenDecoder;
		this.tokenVerifier = tokenVerifier;
		this.contextExtractor = contextExtractor;
		this.contextValidator = contextValidator;
	}


	@Override
	protected C getValidContext(String token) {		
		
		T tokenObject = tokenDecoder.decode(token);
		tokenVerifier.verifyToken(tokenObject);
		C context = contextExtractor.extractContext(tokenObject);
		contextValidator.validateContext(context);
		
		return context;
	}
}
