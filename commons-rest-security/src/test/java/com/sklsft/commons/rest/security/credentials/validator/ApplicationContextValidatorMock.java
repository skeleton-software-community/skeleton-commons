package com.sklsft.commons.rest.security.credentials.validator;

import org.sklsft.commons.rest.security.credentials.validator.SecurityContextValidator;
import org.sklsft.commons.rest.security.exception.InvalidTokenException;

import com.sklsft.commons.rest.security.tokens.SecurityContextMock;

public class ApplicationContextValidatorMock implements SecurityContextValidator<SecurityContextMock> {

	@Override
	public void validateContext(SecurityContextMock context) {
		if (!(context.getApplication().equals("sklgen"))) {
			throw new InvalidTokenException("Bad credentials");
		}
	}
}
