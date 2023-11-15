package com.sklsft.commons.rest.security.credentials.validator;

import org.sklsft.commons.rest.security.credentials.validator.SecurityContextValidator;
import org.sklsft.commons.rest.security.exception.InvalidTokenException;

import com.sklsft.commons.rest.security.tokens.SecurityContextMock;

public class UserContextValidatorMock implements SecurityContextValidator<SecurityContextMock> {

	@Override
	public void validateContext(SecurityContextMock securityCredentials) {
		if (!(securityCredentials.getUser().equals("nicolas.thibault@sklsft.org") && securityCredentials.getApplication().equals("sklgen"))) {
			throw new InvalidTokenException("Bad credentials");
		}
	}
}
