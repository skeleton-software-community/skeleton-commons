package com.sklsft.commons.rest.security.credentials.validator;

import org.sklsft.commons.rest.security.credentials.BasicCredentials;
import org.sklsft.commons.rest.security.credentials.validator.SecurityCredentialsValidator;
import org.sklsft.commons.rest.security.exception.InvalidTokenException;

public class UserCredentialsValidatorMock implements SecurityCredentialsValidator<BasicCredentials> {

	@Override
	public void validateCredentials(BasicCredentials securityCredentials) {
		if (!(securityCredentials.getUser().equals("nicolas.thibault@sklsft.org") && securityCredentials.getApplication().equals("sklgen"))) {
			throw new InvalidTokenException("Bad credentials");
		}
	}
}
