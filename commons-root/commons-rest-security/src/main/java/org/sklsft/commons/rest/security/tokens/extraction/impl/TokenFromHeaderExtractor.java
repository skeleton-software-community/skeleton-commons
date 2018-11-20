package org.sklsft.commons.rest.security.tokens.extraction.impl;

import org.sklsft.commons.rest.security.tokens.extraction.TokenExtractor;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * To extract tokens from Servlet request headers
 * 
 * @author Nicolas Thibault, Abdessalam El Jai, Alexandre Rupp
 *
 */
public class TokenFromHeaderExtractor implements TokenExtractor {

	@Override
	public String extractToken(String key) {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		String result = servletRequestAttributes.getRequest().getHeader(key);
		
		if (result.toLowerCase().startsWith("bearer ")) {
			result = result.substring(7);
		}
		
		return result;
	}
}
