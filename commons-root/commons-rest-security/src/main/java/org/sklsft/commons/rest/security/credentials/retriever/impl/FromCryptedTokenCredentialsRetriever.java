package org.sklsft.commons.rest.security.credentials.retriever.impl;

import org.sklsft.commons.rest.security.credentials.encoder.SecurityCredentialsEncoder;
import org.sklsft.commons.rest.security.credentials.retriever.SecurityCredentialsRetriever;

/**
 *
 *
 * @author Nicolas Thibault, Abdessalam El Jai, Alexandre Rupp
 */
public class FromCryptedTokenCredentialsRetriever<T> implements SecurityCredentialsRetriever<T> {

	private SecurityCredentialsEncoder<T> credentialsEncoder;
	
	
	public FromCryptedTokenCredentialsRetriever(SecurityCredentialsEncoder<T> credentialsEncoder) {
		super();
		this.credentialsEncoder = credentialsEncoder;
		
	}



	@Override
	public T retrieveCredentials(String token) {
		
		T credentials = credentialsEncoder.decode(token);
		return credentials;
	}
}
