package org.sklsft.commons.rest.security.context.impl;

import org.sklsft.commons.rest.security.credentials.extractor.SecurityCredentialsExtractor;
import org.sklsft.commons.rest.security.credentials.validator.SecurityCredentialsValidator;
import org.sklsft.commons.rest.security.tokens.encoder.impl.JwtEncoder;
import org.sklsft.commons.rest.security.tokens.jwt.JsonWebToken;
import org.sklsft.commons.rest.security.tokens.jwt.JwtBody;
import org.sklsft.commons.rest.security.tokens.jwt.JwtHeader;
import org.sklsft.commons.rest.security.tokens.verification.TokenVerifier;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author Nicolas Thibault
 */
public class FromJwtSecurityContextProvider<T extends JsonWebToken<H, B>, H extends JwtHeader, B extends JwtBody, C> extends FromSignedTokenSecurityContextProvider<T, C> {

		
	public FromJwtSecurityContextProvider(ObjectMapper objectMapper, Class<H> headerClass, Class<B> bodyClass, TokenVerifier<T> tokenVerifier,
			SecurityCredentialsExtractor<T, C> credentialsExtractor, SecurityCredentialsValidator<C> credentialsValidator) {
		super(new JwtEncoder<T, H, B>(objectMapper, headerClass, bodyClass), tokenVerifier, credentialsExtractor, credentialsValidator);
		
	}
}
