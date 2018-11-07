package com.sklsft.commons.rest.security.credentials.validator;

import org.sklsft.commons.rest.security.credentials.validator.SecurityCredentialsValidator;
import org.sklsft.commons.rest.security.exception.InvalidTokenException;

import com.sklsft.commons.rest.security.credentials.CredentialsMock;

public class ApplicationSecurityCredentialsValidatorMock implements SecurityCredentialsValidator<CredentialsMock> {

	@Override
	public void validateCredentials(CredentialsMock credentials, String token) {
		if (!(credentials.getApplicationName().equals("Sklgen") && credentials.getApplicationEditor().equals("Skeleton Software Community"))) {
			throw new InvalidTokenException("Bad credentials");
		}
	}
}
