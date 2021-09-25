package org.sklsft.commons.rest.security.context.impl;

import org.sklsft.commons.crypto.accessors.RsaPublicKeyAccessor;
import org.sklsft.commons.crypto.signature.RsaSignatureVerifier;
import org.sklsft.commons.rest.security.credentials.BasicCredentials;
import org.sklsft.commons.rest.security.credentials.extractor.impl.FromBasicJwtCredentialsExtractor;
import org.sklsft.commons.rest.security.credentials.validator.SecurityCredentialsValidator;
import org.sklsft.commons.rest.security.tokens.jwt.BasicJwtBody;
import org.sklsft.commons.rest.security.tokens.jwt.BasicRsaJwtHeader;
import org.sklsft.commons.rest.security.tokens.verification.impl.BasicRsaJwtVerifier;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author Nicolas Thibault
 */
public class FromBasicRsaJwtSecurityContextProvider extends FromJwtSecurityContextProvider<BasicRsaJwtHeader, BasicJwtBody, BasicCredentials> {

		
	public FromBasicRsaJwtSecurityContextProvider(ObjectMapper objectMapper, RsaPublicKeyAccessor rsaPublicKeyAccessor, SecurityCredentialsValidator<BasicCredentials> credentialsValidator) {
		super(objectMapper, BasicRsaJwtHeader.class, BasicJwtBody.class, new BasicRsaJwtVerifier(new RsaSignatureVerifier(rsaPublicKeyAccessor)), new FromBasicJwtCredentialsExtractor(), credentialsValidator);
		
	}
}
