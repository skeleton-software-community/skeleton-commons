package com.sklsft.commons.rest.security.credentials.validator;

import org.sklsft.commons.rest.security.credentials.validator.SecurityCredentialsValidator;
import org.sklsft.commons.rest.security.exception.InvalidTokenException;
import org.sklsft.commons.rest.security.tokens.jwt.BasicJwtBody;

public class UserCredentialsMockValidator implements SecurityCredentialsValidator<BasicJwtBody> {

	@Override
	public void validateCredentials(BasicJwtBody securityCredentials) {
		if (!(securityCredentials.getUser().equals("nicolas.thibault@sklsft.org") && securityCredentials.getApplication().equals("sklgen"))) {
			throw new InvalidTokenException("Bad credentials");
		}
	}
}
