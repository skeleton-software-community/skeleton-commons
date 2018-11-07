package com.sklsft.commons.rest.security.credentials.encoder;

import org.sklsft.commons.rest.security.credentials.encoder.SecurityCredentialsEncoder;
import org.sklsft.commons.rest.security.exception.InvalidTokenException;

import com.sklsft.commons.rest.security.credentials.CredentialsMock;

public class UserSecurityCredentialsEncoderMock implements SecurityCredentialsEncoder<CredentialsMock> {

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
