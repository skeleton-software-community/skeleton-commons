package org.sklsft.commons.rest.security.validation;

public interface SecurityCredentialsValidator<T> {
	
	void validateCredentials(T securityCredentials);

}
