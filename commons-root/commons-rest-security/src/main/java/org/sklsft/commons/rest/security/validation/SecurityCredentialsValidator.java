package org.sklsft.commons.rest.security.validation;

import org.sklsft.commons.rest.security.context.SecurityCredentials;


public interface SecurityCredentialsValidator {
	
	void validateCredentials(SecurityCredentials securityCredentials);

}
