package org.sklsft.commons.rest.security.credentials.retriever.impl;

import org.sklsft.commons.rest.security.credentials.retriever.SecurityCredentialsRetriever;

/**
 *
 *
 * @author Nicolas Thibault, Abdessalam El Jai, Alexandre Rupp
 */
public abstract class FromMapCredentialsRetriever<T> implements SecurityCredentialsRetriever<T> {

	@Override
	public T retrieveCredentials(String token) {
		
		T credentials = get(token);
		
		return credentials;
	}
	
	protected abstract T get(String token);
}
