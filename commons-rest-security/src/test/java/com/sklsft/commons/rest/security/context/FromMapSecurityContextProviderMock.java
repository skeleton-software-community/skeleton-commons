package com.sklsft.commons.rest.security.context;

import java.util.HashMap;
import java.util.Map;

import org.sklsft.commons.rest.security.context.impl.FromKeySecurityContextProvider;
import org.sklsft.commons.rest.security.credentials.BasicCredentials;
import org.sklsft.commons.rest.security.credentials.validator.SecurityCredentialsValidator;
import org.sklsft.commons.rest.security.exception.InvalidTokenException;

/**
 *
 *
 * @author Nicolas Thibault, Abdessalam El Jai, Alexandre Rupp
 */
public class FromMapSecurityContextProviderMock extends FromKeySecurityContextProvider<BasicCredentials> {
	
	public FromMapSecurityContextProviderMock(SecurityCredentialsValidator<BasicCredentials> credentialsValidator) {
		super(credentialsValidator);
	}

	private static Map<String, BasicCredentials> credentials = new HashMap<>();
	
	static {
		BasicCredentials credential = new BasicCredentials();
		credential.setApplication("sklgen");
		credentials.put("sklgen", credential);
		
		credential = new BasicCredentials();
		credential.setApplication("Fake");
		credentials.put("fake", credential);		
	}
	
	@Override
	protected BasicCredentials get(String token) {
		BasicCredentials result = credentials.get(token);
		if (result == null) {
			throw new InvalidTokenException("Invalid Token");
		}
		return result;
	}
}
