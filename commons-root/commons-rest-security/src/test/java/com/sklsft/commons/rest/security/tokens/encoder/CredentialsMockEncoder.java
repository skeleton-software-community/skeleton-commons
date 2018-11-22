package com.sklsft.commons.rest.security.tokens.encoder;

import org.sklsft.commons.rest.security.exception.InvalidTokenException;
import org.sklsft.commons.rest.security.tokens.encoder.TokenEncoder;

import com.sklsft.commons.rest.security.tokens.CredentialsMock;

public class CredentialsMockEncoder implements TokenEncoder<CredentialsMock> {

	@Override
	public CredentialsMock decode(String token) {

		String[] tokens = token.split("\\$");
		
		if (tokens.length <2) {
			throw new InvalidTokenException("Bad token");
		}
		
		CredentialsMock result = new CredentialsMock();
		result.setUserFirstName(tokens[0]);
		result.setUserLastName(tokens[1]);
		
		return result;
	}

	@Override
	public String encode(CredentialsMock credentials) {
		String result = "";
		result += credentials.getUserFirstName();
		result += "$";
		result += credentials.getUserLastName();
		
		return result;
	}
}
