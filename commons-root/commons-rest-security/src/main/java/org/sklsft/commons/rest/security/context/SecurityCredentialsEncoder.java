package org.sklsft.commons.rest.security.context;


/**
 * used to encode/decode a SecurityCredentials as a String
 * @author Nicolas Thibault
 *
 */
public interface SecurityCredentialsEncoder<T> {
	
	T decode (String token);
	
	String encode (Object credentials);
}
