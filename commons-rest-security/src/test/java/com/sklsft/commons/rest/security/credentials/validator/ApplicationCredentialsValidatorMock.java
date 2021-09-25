package com.sklsft.commons.rest.security.credentials.validator;

import org.sklsft.commons.rest.security.credentials.BasicCredentials;
import org.sklsft.commons.rest.security.credentials.validator.SecurityCredentialsValidator;
import org.sklsft.commons.rest.security.exception.InvalidTokenException;

public class ApplicationCredentialsValidatorMock implements SecurityCredentialsValidator<BasicCredentials> {

	@Override
	public void validateCredentials(BasicCredentials credentials) {
		if (!(credentials.getApplication().equals("sklgen"))) {
			throw new InvalidTokenException("Bad credentials");
		}
	}
}
