package com.sklsft.commons.rest.security.tokens.encoder;

import org.sklsft.commons.rest.security.exception.InvalidTokenException;
import org.sklsft.commons.rest.security.tokens.encoder.TokenEncoder;
import org.sklsft.commons.rest.security.tokens.jwt.BasicJwtBody;

public class BasicCredentialsMockEncoder implements TokenEncoder<BasicJwtBody> {

	@Override
	public BasicJwtBody decode(String token) {

		String[] tokens = token.split("\\$");
		
		if (tokens.length <2) {
			throw new InvalidTokenException("Bad token");
		}
		
		BasicJwtBody result = new BasicJwtBody();
		result.setApplication(tokens[0]);
		result.setUser(tokens[1]);		
		
		return result;
	}

	@Override
	public String encode(BasicJwtBody credentials) {
		String result = "";
		result += credentials.getApplication();
		result += "$";
		result += credentials.getUser();
		
		return result;
	}
}
