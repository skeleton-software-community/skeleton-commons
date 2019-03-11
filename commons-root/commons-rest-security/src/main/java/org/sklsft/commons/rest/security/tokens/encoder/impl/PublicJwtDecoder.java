package org.sklsft.commons.rest.security.tokens.encoder.impl;

import org.sklsft.commons.rest.security.tokens.jwt.JsonWebToken;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author Nicolas Thibault
 *
 * @param <T>
 * @param <H>
 * @param <B>
 */
public class PublicJwtDecoder<T extends JsonWebToken<H, B>, H, B> extends JwtEncoder<T, H, B> {
	
	
	public PublicJwtDecoder(ObjectMapper objectMapper, Class<H> headerClass, Class<B> bodyClass) {
		super(objectMapper, headerClass, bodyClass);
	}
	

	protected byte[] sign(byte[] payload) {
		throw new IllegalAccessError("Cannot sign !");
	}

}
