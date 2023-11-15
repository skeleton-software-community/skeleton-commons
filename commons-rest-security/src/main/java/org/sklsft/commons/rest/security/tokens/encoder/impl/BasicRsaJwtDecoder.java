package org.sklsft.commons.rest.security.tokens.encoder.impl;

import org.sklsft.commons.rest.security.tokens.jwt.BasicJwtBody;
import org.sklsft.commons.rest.security.tokens.jwt.BasicRsaJsonWebToken;
import org.sklsft.commons.rest.security.tokens.jwt.RsaJwtHeader;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author Nicolas Thibault
 *
 */
public class BasicRsaJwtDecoder extends JwtDecoder<BasicRsaJsonWebToken, RsaJwtHeader, BasicJwtBody> {
	
	
	public BasicRsaJwtDecoder(ObjectMapper objectMapper) {
		super(objectMapper, BasicRsaJsonWebToken.class, RsaJwtHeader.class, BasicJwtBody.class);
	}
}
