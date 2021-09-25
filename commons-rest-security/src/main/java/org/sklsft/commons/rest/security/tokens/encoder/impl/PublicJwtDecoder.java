package org.sklsft.commons.rest.security.tokens.encoder.impl;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author Nicolas Thibault
 *
 * @param <T>
 * @param <H>
 * @param <B>
 */
public class PublicJwtDecoder<H, B> extends JwtEncoder<H, B> {
	
	
	public PublicJwtDecoder(ObjectMapper objectMapper, Class<H> headerClass, Class<B> bodyClass) {
		super(objectMapper, headerClass, bodyClass);
	}
	

	protected byte[] sign(byte[] payload) {
		throw new IllegalAccessError("Cannot sign !");
	}

}
