package org.sklsft.commons.rest.security.tokens.verification.impl;

import org.sklsft.commons.rest.security.tokens.verification.TokenVerifier;

/**
 *
 * @param <T>
 * 
 * @author Nicolas Thibault
 *
 
 */
public class JwtVerifier<T> implements TokenVerifier<T> {

	@Override
	public void verifyToken(T token) {
		//Does nothing
	}

}
