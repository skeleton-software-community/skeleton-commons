package org.sklsft.commons.rest.security.tokens.impl;

import org.sklsft.commons.rest.security.tokens.TokenExtractionMode;
import org.sklsft.commons.rest.security.tokens.TokenExtractor;


/**
 * Chooses the correct tokens extractor depending on the {@link TokenExtractionMode}
 * 
 * @author Nicolas Thibault, Abdessalam El Jai, Alexandre Rupp
 *
 */
public class TokenExtractorFactory {

	public static TokenExtractor getTokenExtractor(TokenExtractionMode mode) {
		switch (mode) {
			case HEADER:
				return new TokenFromHeaderExtractor();
				
			case COOKIE:
				return new TokenFromCookieExtractor(new TokenFromHeaderExtractor());
	
			default:
				throw new IllegalArgumentException("mode " + mode.name() + " not supported for token extraction");
		}
	}
}
