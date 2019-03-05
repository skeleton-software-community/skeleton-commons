package org.sklsft.commons.rest.security.tokens.encoder.impl;

import org.sklsft.commons.crypto.accessors.AesKeyAccessor;
import org.sklsft.commons.crypto.encoding.AesJsonObjectEncoder;
import org.sklsft.commons.crypto.encoding.ObjectEncoder;
import org.sklsft.commons.rest.security.exception.InvalidTokenException;
import org.sklsft.commons.rest.security.tokens.encoder.TokenEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * imlementation of a {@link TokenEncoder} that uses an {@link AesJsonObjectEncoder}
 *
 * @author Nicolas Thibault, Abdessalam El Jai, Alexandre Rupp
 */
public class AesJsonTokenEncoder<T> implements TokenEncoder<T> {
	
	private ObjectEncoder objectEncoder;
	private Class<T> tokenClass;
	
	
	public AesJsonTokenEncoder(ObjectMapper objectMapper, AesKeyAccessor keyAccessor, Class<T> tokenClass) {	
		this.objectEncoder = new AesJsonObjectEncoder(objectMapper, keyAccessor);
		this.tokenClass = tokenClass;
	}	
	
	
	public T decode (String token) {
		
		try {
			return objectEncoder.decode(token, tokenClass);
		} catch (Exception e) {
			throw new InvalidTokenException("token.invalid", e);
		}
	}
	
	public String encode (T token) {
		
		return objectEncoder.encode(token);
	}
}
