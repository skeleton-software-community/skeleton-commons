package com.sklsft.commons.rest.security.credentials;

import org.sklsft.commons.api.exception.rights.AccessDeniedException;
import org.sklsft.commons.rest.security.credentials.SecurityCredentialsValidator;

public class UserSecurityCredentialsValidatorMock implements SecurityCredentialsValidator<UserCredentialsMock> {

	@Override
	public void validateCredentials(UserCredentialsMock securityCredentials) {
		if (!(securityCredentials.getFirstName().equals("Nicolas") && securityCredentials.getLastName().equals("Thibault"))) {
			throw new AccessDeniedException("Bad credentials");
		}
	}
}
