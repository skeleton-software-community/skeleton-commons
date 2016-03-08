package org.sklsft.commons.rest.security.aspect;

import org.sklsft.commons.rest.security.context.SecurityContextHolder;
import org.sklsft.commons.rest.security.context.SecurityCredentialsEncoder;
import org.sklsft.commons.rest.security.validation.SecurityCredentialsValidator;

/**
 * this class is responsible for creating and destroying a security context given a token
 * this security context will be handled by a ThreadLocal so that it will be accessible in the hole Thread execution's scope
 * 
 * @author Nicolas Thibault
 *
 */
public class SecurityContextProvider<T> {
	
	private SecurityCredentialsEncoder<T> decoder;
	private SecurityCredentialsValidator<T> validator;
	
		
	public SecurityContextProvider(SecurityCredentialsEncoder<T> decoder,
			SecurityCredentialsValidator<T> validator) {
		super();
		this.decoder = decoder;
		this.validator = validator;
	}


	public void provideSecurityContext(String token) {
		
		T credentials = decoder.decode(token);
		
		validator.validateCredentials(credentials);
		
		SecurityContextHolder.bindCredentials(credentials);		
	}
	
	
	public void clearSecurityContext() {
		SecurityContextHolder.unbindCredentials();
	}
}
