package org.sklsft.commons.rest.security.aspect;

import org.sklsft.commons.rest.security.SecurityCredentialsEncoder;
import org.sklsft.commons.rest.security.context.SecurityContextHolder;
import org.sklsft.commons.rest.security.context.SecurityCredentials;
import org.sklsft.commons.rest.security.validation.SecurityCredentialsValidator;

/**
 * this class is responsible for creating and destroying a security context as a {@link SecurityCredentials} given a token
 * this security context will be handled by a ThreadLocal so that it will be accessible in the hole Thread execution's scope
 * 
 * @author Nicolas Thibault
 *
 */
public class SecurityContextProvider {
	
	private SecurityCredentialsEncoder decoder;
	private SecurityCredentialsValidator validator;
	
		
	public SecurityContextProvider(SecurityCredentialsEncoder decoder,
			SecurityCredentialsValidator validator) {
		super();
		this.decoder = decoder;
		this.validator = validator;
	}


	public void provideSecurityContext(String token) {
		
		SecurityCredentials credentials = decoder.decode(token);
		
		validator.validateCredentials(credentials);		
		
		SecurityContextHolder.bindCredentials(credentials);		
	}
	
	
	public void clearSecurityContext() {
		SecurityContextHolder.unbindCredentials();
	}
}
