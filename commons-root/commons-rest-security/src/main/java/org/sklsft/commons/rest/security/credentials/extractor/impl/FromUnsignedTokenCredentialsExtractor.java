package org.sklsft.commons.rest.security.credentials.extractor.impl;

import org.sklsft.commons.rest.security.credentials.extractor.SecurityCredentialsExtractor;

/**
 * For Symetric crypted tokens, the credentials are the token object itself
 *
 * @author Nicolas Thibault, Abdessalam El Jai, Alexandre Rupp
 */
public class FromUnsignedTokenCredentialsExtractor<T> implements SecurityCredentialsExtractor<T, T> {

	@Override
	public T extractCredentials(T token) {
		
		return token;
	}
}
