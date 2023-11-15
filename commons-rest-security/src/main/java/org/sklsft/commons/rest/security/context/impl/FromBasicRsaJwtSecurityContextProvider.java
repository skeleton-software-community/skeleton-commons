package org.sklsft.commons.rest.security.context.impl;

import org.sklsft.commons.rest.security.credentials.validator.SecurityContextValidator;
import org.sklsft.commons.rest.security.tokens.encoder.impl.BasicRsaJwtDecoder;
import org.sklsft.commons.rest.security.tokens.jwt.BasicJwtBody;
import org.sklsft.commons.rest.security.tokens.jwt.BasicRsaJsonWebToken;
import org.sklsft.commons.rest.security.tokens.jwt.RsaJwtHeader;
import org.sklsft.commons.rest.security.tokens.verification.impl.BasicRsaJwtVerifier;

/**
 * 
 * @author Nicolas Thibault
 */
public class FromBasicRsaJwtSecurityContextProvider extends FromJwtSecurityContextProvider<BasicRsaJsonWebToken, RsaJwtHeader, BasicJwtBody> {

	public FromBasicRsaJwtSecurityContextProvider(BasicRsaJwtDecoder decoder, BasicRsaJwtVerifier verifier, SecurityContextValidator<BasicJwtBody> contextValidator) {
		super(decoder, verifier, contextValidator);
		
	}
}
