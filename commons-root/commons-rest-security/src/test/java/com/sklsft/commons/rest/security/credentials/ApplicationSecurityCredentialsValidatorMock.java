package com.sklsft.commons.rest.security.credentials;

import org.sklsft.commons.api.exception.rights.AccessDeniedException;
import org.sklsft.commons.rest.security.credentials.SecurityCredentialsValidator;

public class ApplicationSecurityCredentialsValidatorMock implements SecurityCredentialsValidator<ApplicationCredentialsMock> {

	@Override
	public void validateCredentials(ApplicationCredentialsMock securityCredentials) {
		if (!(securityCredentials.getName().equals("Sklgen") && securityCredentials.getEditor().equals("Skeleton Software Community"))) {
			throw new AccessDeniedException("Bad credentials");
		}
	}
}
