package org.sklsft.commons.rest.security.credentials.retriever;

import org.sklsft.commons.rest.security.credentials.retriever.impl.FromCryptedTokenCredentialsRetriever;

/**
 * Provides Credentials from the Token
 * Several implementations are possible :
 * <li>We can decode then validate the credentials : {@link FromCryptedTokenCredentialsRetriever}
 * <li>We can link the token to credentials with an access to a licences repository
 * 
 * @author Nicolas Thibault, Abdessalam El Jai, Alexandre Rupp
 *
 * @param <T> : The Credentials type
 */
public interface SecurityCredentialsRetriever<T> {

	T retrieveCredentials(String token);
}
