package com.sklsft.commons.rest.security.context;

import java.util.HashMap;
import java.util.Map;

import org.sklsft.commons.rest.security.context.impl.FromKeySecurityContextProvider;
import org.sklsft.commons.rest.security.credentials.validator.SecurityContextValidator;
import org.sklsft.commons.rest.security.exception.InvalidTokenException;

import com.sklsft.commons.rest.security.tokens.SecurityContextMock;

/**
 *
 *
 * @author Nicolas Thibault, Abdessalam El Jai, Alexandre Rupp
 */
public class FromMapSecurityContextProviderMock extends FromKeySecurityContextProvider<SecurityContextMock> {
	
	public FromMapSecurityContextProviderMock(SecurityContextValidator<SecurityContextMock> validator) {
		super(validator);
	}

	private static Map<String, SecurityContextMock> contexts = new HashMap<>();
	
	static {
		SecurityContextMock context = new SecurityContextMock();
		context.setApplication("sklgen");
		contexts.put("sklgen", context);
		
		context = new SecurityContextMock();
		context.setApplication("Fake");
		contexts.put("fake", context);		
	}
	
	@Override
	protected SecurityContextMock get(String token) {
		SecurityContextMock result = contexts.get(token);
		if (result == null) {
			throw new InvalidTokenException("Invalid Token");
		}
		return result;
	}
}
