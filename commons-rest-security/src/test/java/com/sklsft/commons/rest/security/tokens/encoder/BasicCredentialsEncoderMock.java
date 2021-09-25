package com.sklsft.commons.rest.security.tokens.encoder;

import org.sklsft.commons.rest.security.credentials.BasicCredentials;
import org.sklsft.commons.rest.security.exception.InvalidTokenException;
import org.sklsft.commons.rest.security.tokens.encoder.TokenEncoder;

public class BasicCredentialsEncoderMock implements TokenEncoder<BasicCredentials> {

	@Override
	public BasicCredentials decode(String token) {

		String[] tokens = token.split("\\$");
		
		if (tokens.length <2) {
			throw new InvalidTokenException("Bad token");
		}
		
		BasicCredentials result = new BasicCredentials();
		result.setApplication(tokens[0]);
		result.setUser(tokens[1]);		
		
		return result;
	}

	@Override
	public String encode(BasicCredentials credentials) {
		String result = "";
		result += credentials.getApplication();
		result += "$";
		result += credentials.getUser();
		
		return result;
	}
}
