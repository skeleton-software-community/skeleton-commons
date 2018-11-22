package org.sklsft.commons.rest.security.tokens.encoder.impl;

import org.sklsft.commons.rest.security.tokens.encoder.TokenEncoder;

/**
 * For tokens that represents a plain license key
 * 
 * @author Nicolas Thibault
 *
 */
public class PlainTextTokenEncoder implements TokenEncoder<String> {

	@Override
	public String decode(String token) {
		return token;
	}

	@Override
	public String encode(String credentials) {
		return credentials;
	}

}
