package org.sklsft.commons.rest.security.tokens.extraction;

import org.sklsft.commons.rest.security.tokens.extraction.impl.TokenExtractorFactory;

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
