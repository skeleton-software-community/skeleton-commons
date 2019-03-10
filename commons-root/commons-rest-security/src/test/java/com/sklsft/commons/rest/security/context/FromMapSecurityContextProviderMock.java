package com.sklsft.commons.rest.security.context;

import java.util.HashMap;
import java.util.Map;

import org.sklsft.commons.rest.security.context.impl.FromKeySecurityContextProvider;
import org.sklsft.commons.rest.security.credentials.validator.SecurityCredentialsValidator;
import org.sklsft.commons.rest.security.exception.InvalidTokenException;
import org.sklsft.commons.rest.security.tokens.jwt.BasicJwtBody;

/**
 *
 *
 * @author Nicolas Thibault, Abdessalam El Jai, Alexandre Rupp
 */
public class FromMapSecurityContextProviderMock extends FromKeySecurityContextProvider<BasicJwtBody> {
	
	public FromMapSecurityContextProviderMock(SecurityCredentialsValidator<BasicJwtBody> credentialsValidator) {
		super(credentialsValidator);
	}

	private static Map<String, BasicJwtBody> credentials = new HashMap<>();
	
	static {
		BasicJwtBody credential = new BasicJwtBody();
		credential.setApplication("sklgen");
		credentials.put("sklgen", credential);
		
		credential = new BasicJwtBody();
		credential.setApplication("Fake");
		credentials.put("fake", credential);		
	}
	
	@Override
	protected BasicJwtBody get(String token) {
		BasicJwtBody result = credentials.get(token);
		if (result == null) {
			throw new InvalidTokenException("Invalid Token");
		}
		return result;
	}
}
