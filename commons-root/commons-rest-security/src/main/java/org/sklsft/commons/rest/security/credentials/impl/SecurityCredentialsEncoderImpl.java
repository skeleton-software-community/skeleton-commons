package org.sklsft.commons.rest.security.credentials.impl;

import org.sklsft.commons.crypto.ObjectEncoder;
import org.sklsft.commons.rest.security.credentials.SecurityCredentialsEncoder;
import org.sklsft.commons.rest.security.exception.InvalidTokenException;

/**
 * imlementation of a {@link SecurityCredentialsEncoder} that uses an {@link ObjectEncoder}
 *
 * @author Nicolas Thibault, Abdessalam El Jai, Alexandre Rupp
 */
public class SecurityCredentialsEncoderImpl<T> implements SecurityCredentialsEncoder<T> {
	
	private ObjectEncoder objectEncoder;
	private Class<T> credentialsClass;
	
	
	public SecurityCredentialsEncoderImpl(ObjectEncoder objectEncoder, Class<T> credentialsClass) {	
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
	
	public String encode (T credentials) {
		
		return objectEncoder.encode(credentials);
	}
}
