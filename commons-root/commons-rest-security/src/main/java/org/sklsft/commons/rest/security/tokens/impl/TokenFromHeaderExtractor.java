package org.sklsft.commons.rest.security.tokens.impl;

import org.sklsft.commons.rest.security.tokens.TokenExtractor;
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
		return servletRequestAttributes.getRequest().getHeader(key);
	}
}
