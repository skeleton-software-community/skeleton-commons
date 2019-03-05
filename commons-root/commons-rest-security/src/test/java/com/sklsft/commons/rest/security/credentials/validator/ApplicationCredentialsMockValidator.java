package com.sklsft.commons.rest.security.credentials.validator;

import org.sklsft.commons.rest.security.credentials.validator.SecurityCredentialsValidator;
import org.sklsft.commons.rest.security.exception.InvalidTokenException;
import org.sklsft.commons.rest.security.tokens.jwt.BasicCredentials;

public class ApplicationCredentialsMockValidator implements SecurityCredentialsValidator<BasicCredentials> {

	@Override
	public void validateCredentials(BasicCredentials credentials) {
		if (!(credentials.getApplication().equals("sklgen"))) {
			throw new InvalidTokenException("Bad credentials");
		}
	}
}
