package org.sklsft.commons.rest.security;

import org.sklsft.commons.crypto.ObjectEncoder;
import org.sklsft.commons.rest.security.exception.InvalidTokenException;

/**
 * A component to encode/decode a {@link SecurityCredentials} as a String
 * @author Nicolas Thibault
 *
 */
public class SecurityCredentialsEncoder<T> {
	
	private ObjectEncoder objectEncoder;
	private Class<T> credentialsClass;
	
	
	public SecurityCredentialsEncoder(ObjectEncoder objectEncoder, Class<T> credentialsClass) {	
		this.objectEncoder = objectEncoder;
		this.credentialsClass = credentialsClass;
	}	
	
	
	public T decode (String token) {
		
		try {
			return objectEncoder.decode(token, credentialsClass);
		} catch (Exception e) {
			throw new InvalidTokenException("token.invalid", e);
		}
	}
	
	public String encode (Object credentials) {
		
		return objectEncoder.encode(credentials);
	}
}
