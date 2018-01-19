package org.sklsft.commons.rest.security.credentials.impl;

import org.sklsft.commons.rest.security.credentials.SecurityCredentialsEncoder;
import org.sklsft.commons.rest.security.credentials.SecurityCredentialsRetriever;
import org.sklsft.commons.rest.security.credentials.SecurityCredentialsValidator;

/**
 *
 *
 * @author Nicolas Thibault, Abdessalam El Jai, Alexandre Rupp
 */
public class FromCryptedTokenCredentialsRetriever<T> implements SecurityCredentialsRetriever<T> {

	private SecurityCredentialsEncoder<T> credentialsEncoder;
	private SecurityCredentialsValidator<T> credentialsValidator;
	
	
	public FromCryptedTokenCredentialsRetriever(SecurityCredentialsEncoder<T> credentialsEncoder,
			SecurityCredentialsValidator<T> credentialsValidator) {
		super();
		this.credentialsEncoder = credentialsEncoder;
		this.credentialsValidator = credentialsValidator;
	}



	@Override
	public T retrieveCredentials(String token) {
		
		T credentials = credentialsEncoder.decode(token);
		credentialsValidator.validateCredentials(credentials);		
		return credentials;
	}
}
