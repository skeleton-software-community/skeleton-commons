package org.sklsft.commons.rest.security.credentials.extractor;

/**
 * Provides Credentials from the Token
 * 
 *
 * @param <T> : The Token type
 * @param <C> : The Credentials type
 * 
 * @author Nicolas Thibault, Abdessalam El Jai, Alexandre Rupp
 */
public interface SecurityCredentialsExtractor<T, C> {

	C getCredentials(T token);
}
