package com.sklsft.commons.rest.security.credentials.validator;

import org.sklsft.commons.rest.security.credentials.validator.SecurityContextValidator;
import org.sklsft.commons.rest.security.exception.InvalidTokenException;
import org.sklsft.commons.rest.security.tokens.jwt.BasicJwtBody;

public class BasicJwtBodyValidatorMock implements SecurityContextValidator<BasicJwtBody> {

	@Override
	public void validateContext(BasicJwtBody securityCredentials) {
		if (!securityCredentials.getUser().equals("nicolas.thibault@sklsft.org")) {
			throw new InvalidTokenException("Bad credentials");
		}
	}
}
