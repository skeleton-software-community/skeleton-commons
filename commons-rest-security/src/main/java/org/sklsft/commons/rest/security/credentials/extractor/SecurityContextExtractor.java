package org.sklsft.commons.rest.security.credentials.extractor;

/**
 * Extracts context from the Token
 * 
 *
 * @param <T> : The Token type
 * @param <C> : The Context type
 * 
 * @author Nicolas Thibault, Abdessalam El Jai, Alexandre Rupp
 */
public interface SecurityContextExtractor<T, C> {

	C extractContext(T token);
}
