package org.sklsft.commons.rest.security;

import org.sklsft.commons.crypto.ObjectEncoder;
import org.sklsft.commons.crypto.EncryptionParametersAccessor;
import org.sklsft.commons.rest.security.context.SecurityCredentials;
import org.sklsft.commons.rest.security.exception.InvalidTokenException;

/**
 * A component to encode/decode a {@link SecurityCredentials} as a String
 * @author Nicolas Thibault
 *
 */
public class SecurityCredentialsEncoder {
	
	private ObjectEncoder objectEncoder;	
	
	private Class<? extends SecurityCredentials> crendentialsClass;
	
	
	@SuppressWarnings("unchecked")
	public SecurityCredentialsEncoder(ObjectEncoder objectEncoder, String crendentialsClassName) {
		
		this.objectEncoder = objectEncoder;
		
		
		@SuppressWarnings("rawtypes")
		Class clazz;
		
		try {
			clazz = Class.forName(crendentialsClassName);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException(e.getMessage(), e);
		}
		
		if (!SecurityCredentials.class.isAssignableFrom(clazz)) {
			throw new IllegalArgumentException(crendentialsClassName + "does not implements " + SecurityCredentials.class.getName());
		}
		
		this.crendentialsClass = clazz;
	}	
	
	
	public SecurityCredentials decode (String token) {
		
		try {
			return objectEncoder.decode(token, crendentialsClass);
		} catch (Exception e) {
			throw new InvalidTokenException("token.invalid", e);
		}
	}
	
	public String encode (SecurityCredentials credentials) {
		
		return objectEncoder.encode(credentials);
	}
}
