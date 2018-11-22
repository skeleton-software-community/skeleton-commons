package org.sklsft.commons.rest.security.credentials.extractor.impl;

import org.sklsft.commons.rest.security.credentials.extractor.SecurityCredentialsExtractor;

/**
 * This is adapted for tokens that represent a plain license key where you have to find a correspondance in a repository
 *
 * @author Nicolas Thibault, Abdessalam El Jai, Alexandre Rupp
 */
public abstract class FromMapCredentialsExtractor<C> implements SecurityCredentialsExtractor<String, C> {

	@Override
	public C extractCredentials(String token) {
		
		C credentials = get(token);
		
		return credentials;
	}
	
	protected abstract C get(String token);
}
