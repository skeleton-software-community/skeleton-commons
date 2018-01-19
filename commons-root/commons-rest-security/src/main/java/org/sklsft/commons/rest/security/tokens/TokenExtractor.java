package org.sklsft.commons.rest.security.tokens;

import org.sklsft.commons.rest.security.tokens.impl.TokenExtractorFactory;

/**
 * 
 * To define an interface linked with a factory {@link TokenExtractorFactory}
 * 
 * @author Nicolas Thibault, Abdessalam El Jai, Alexandre Rupp
 *
 */
public interface TokenExtractor {

	String extractToken(String key);
}
