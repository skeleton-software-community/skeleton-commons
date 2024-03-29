package org.sklsft.commons.rest.security.tokens.extraction.impl;

import org.sklsft.commons.rest.security.tokens.extraction.TokenExtractor;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.Cookie;

/**
 * To extract tokens from Servlet request cookies
 * 
 * @author Nicolas Thibault, Abdessalam El Jai, Alexandre Rupp
 *
 */
public class TokenFromCookieExtractor implements TokenExtractor {
	
	private TokenFromHeaderExtractor tokenFromHeaderExtractor;

	public TokenFromCookieExtractor(TokenFromHeaderExtractor tokenFromHeaderExtractor) {
		super();
		this.tokenFromHeaderExtractor = tokenFromHeaderExtractor;
	}



	@Override
	public String extractToken(String key) {
		
		String result = tokenFromHeaderExtractor.extractToken(key);
		
		if (result != null) {
			return result;
		}
		
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		Cookie[] cookies = servletRequestAttributes.getRequest().getCookies();
		
		if (cookies!=null) {
			for(Cookie cookie : cookies){
				if(cookie.getName().equals(key)){
					result = cookie.getValue();
					return result;
				}
			}
		}
		return result;		
	}
}
