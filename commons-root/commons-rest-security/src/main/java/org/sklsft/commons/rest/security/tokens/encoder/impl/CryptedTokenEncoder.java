package org.sklsft.commons.rest.security.tokens.encoder.impl;

import org.sklsft.commons.crypto.accessors.AesKeyAccessor;
import org.sklsft.commons.crypto.encoding.ObjectEncoder;
import org.sklsft.commons.rest.security.exception.InvalidTokenException;
import org.sklsft.commons.rest.security.tokens.encoder.TokenEncoder;

/**
 * imlementation of a {@link TokenEncoder} that uses an {@link ObjectEncoder}
 *
 * @author Nicolas Thibault, Abdessalam El Jai, Alexandre Rupp
 */
public class CryptedTokenEncoder<T> implements TokenEncoder<T> {
	
	private ObjectEncoder objectEncoder;
	private Class<T> tokenClass;
	
	
	public CryptedTokenEncoder(ObjectEncoder objectEncoder, Class<T> tokenClass) {	
		this.objectEncoder = objectEncoder;
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
