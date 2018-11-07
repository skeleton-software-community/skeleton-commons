package org.sklsft.commons.rest.security.credentials.encoder;


/**
 * used to encode/decode a SecurityCredentials to/from a String
 * 
 * @author Nicolas Thibault, Abdessalam El Jai, Alexandre Rupp
 *
 */
public interface SecurityCredentialsEncoder<T> {
	
	T decode (String token);
	
	String encode (T credentials);
}
