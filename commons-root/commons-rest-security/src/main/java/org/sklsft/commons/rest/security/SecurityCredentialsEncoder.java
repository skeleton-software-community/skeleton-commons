package org.sklsft.commons.rest.security;

import org.sklsft.commons.crypto.ObjectEncoder;
import org.sklsft.commons.rest.security.context.SecurityCredentials;
import org.sklsft.commons.rest.security.exception.InvalidTokenException;

/**
 * A component to encode/decode a {@link SecurityCredentials} as a String
 * @author Nicolas Thibault
 *
 */
public class SecurityCredentialsEncoder {
	
	private ObjectEncoder objectEncoder;

	private TokensEncryptionParametersAccessor parametersAccessor;
	
	private Class<? extends SecurityCredentials> crendentialsClass;
	
	
	@SuppressWarnings("unchecked")
	public SecurityCredentialsEncoder(ObjectEncoder objectEncoder, TokensEncryptionParametersAccessor parametersAccessor, String crendentialsClassName) {
		
		this.objectEncoder = objectEncoder;
		this.parametersAccessor = parametersAccessor;
		
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
		
		byte[] key = parametersAccessor.getTokenEncryptionKey();
		String symetricAlgorithm = parametersAccessor.getTokenEncryptionSymmetricAlgorithm();
		
		try {
			return objectEncoder.decode(token, crendentialsClass, symetricAlgorithm, key);
		} catch (Exception e) {
			throw new InvalidTokenException("token.invalid", e);
		}
	}
	
	public String encode (SecurityCredentials credentials) {
		
		byte[] key = parametersAccessor.getTokenEncryptionKey();
		String symmetricAlgorithm = parametersAccessor.getTokenEncryptionSymmetricAlgorithm();
		
		return objectEncoder.encode(credentials, symmetricAlgorithm, key);
	}
}
