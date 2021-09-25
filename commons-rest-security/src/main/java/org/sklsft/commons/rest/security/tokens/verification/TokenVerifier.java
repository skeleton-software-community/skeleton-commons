package org.sklsft.commons.rest.security.tokens.verification;

public interface TokenVerifier<T> {

	/**
	 * Should be used for JWT signature verification. <br>
	 * For symetrically crypted token (unsigned), there is nothing to verify...
	 * 
	 * @param token
	 * 
	 * @author Nicolas Thibault
	 */
	public void verifyToken(T token);
}
