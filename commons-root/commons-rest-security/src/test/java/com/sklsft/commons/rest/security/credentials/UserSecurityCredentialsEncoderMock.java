package com.sklsft.commons.rest.security.credentials;

import org.sklsft.commons.rest.security.credentials.SecurityCredentialsEncoder;

public class UserSecurityCredentialsEncoderMock implements SecurityCredentialsEncoder<UserCredentialsMock> {

	@Override
	public UserCredentialsMock decode(String token) {
		
		String[] tokens = token.split("\\$");
		
		UserCredentialsMock result = new UserCredentialsMock();
		result.setFirstName(tokens[0]);
		result.setLastName(tokens[1]);
		
		return result;
	}

	@Override
	public String encode(UserCredentialsMock credentials) {
		String result = "";
		result += credentials.getFirstName();
		result += "$";
		result += credentials.getLastName();
		
		return result;
	}
}
