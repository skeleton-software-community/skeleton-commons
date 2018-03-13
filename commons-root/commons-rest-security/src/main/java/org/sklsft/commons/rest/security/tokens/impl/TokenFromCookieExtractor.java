package org.sklsft.commons.rest.security.tokens.impl;

import javax.servlet.http.Cookie;

import org.sklsft.commons.rest.security.tokens.TokenExtractor;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
		
		for(Cookie cookie : cookies){
			if(cookie.getName().equals(key)){
				result = cookie.getValue();
				break;
			}
		}
		
		return result;
	}
}
