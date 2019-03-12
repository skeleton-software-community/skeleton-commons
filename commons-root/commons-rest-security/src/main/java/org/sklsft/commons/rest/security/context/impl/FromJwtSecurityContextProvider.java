package org.sklsft.commons.rest.security.context.impl;

import org.sklsft.commons.rest.security.credentials.extractor.SecurityCredentialsExtractor;
import org.sklsft.commons.rest.security.credentials.validator.SecurityCredentialsValidator;
import org.sklsft.commons.rest.security.tokens.encoder.impl.PublicJwtDecoder;
import org.sklsft.commons.rest.security.tokens.jwt.JsonWebToken;
import org.sklsft.commons.rest.security.tokens.verification.TokenVerifier;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author Nicolas Thibault
 */
public class FromJwtSecurityContextProvider<H, B, C> extends FromSignedTokenSecurityContextProvider<JsonWebToken<H, B>, C> {

		
	public FromJwtSecurityContextProvider(ObjectMapper objectMapper, Class<H> headerClass, Class<B> bodyClass, TokenVerifier<JsonWebToken<H, B>> tokenVerifier,
			SecurityCredentialsExtractor<JsonWebToken<H, B>, C> credentialsExtractor, SecurityCredentialsValidator<C> credentialsValidator) {
		super(new PublicJwtDecoder<H, B>(objectMapper, headerClass, bodyClass), tokenVerifier, credentialsExtractor, credentialsValidator);
		
	}
}
