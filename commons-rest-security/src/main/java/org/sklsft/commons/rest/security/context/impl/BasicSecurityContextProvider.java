package org.sklsft.commons.rest.security.context.impl;

import org.sklsft.commons.rest.security.context.SecurityContextHolder;
import org.sklsft.commons.rest.security.context.SecurityContextProvider;
import org.sklsft.commons.rest.security.exception.TokenNotFoundException;

/**
 * 
 * 
 * @author Nicolas Thibault
 */
public abstract class BasicSecurityContextProvider<C> implements SecurityContextProvider {


	@Override
	public void provideSecurityContext(String token) {		
		if (token == null) {
			throw new TokenNotFoundException("token.notFound");
		}
		C context = getValidContext(token);
		SecurityContextHolder.bindContext(context);
	}

	protected abstract C getValidContext(String token);
}
