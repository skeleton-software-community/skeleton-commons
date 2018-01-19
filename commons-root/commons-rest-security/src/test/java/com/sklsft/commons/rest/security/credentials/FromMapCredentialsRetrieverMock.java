package com.sklsft.commons.rest.security.credentials;

import java.util.HashMap;
import java.util.Map;

import org.sklsft.commons.api.exception.rights.AccessDeniedException;
import org.sklsft.commons.rest.security.credentials.SecurityCredentialsValidator;
import org.sklsft.commons.rest.security.credentials.impl.FromMapCredentialsRetriever;

/**
 *
 *
 * @author Nicolas Thibault, Abdessalam El Jai, Alexandre Rupp
 */
public class FromMapCredentialsRetrieverMock extends FromMapCredentialsRetriever<ApplicationCredentialsMock> {
	
	private static Map<String, ApplicationCredentialsMock> credentials = new HashMap<>();
	
	static {
		ApplicationCredentialsMock credential = new ApplicationCredentialsMock();
		credential.setName("Sklgen");
		credential.setEditor("Skeleton Software Community");
		credentials.put("Sklgen", credential);
		
		credential = new ApplicationCredentialsMock();
		credential.setName("Fake");
		credential.setEditor("Skeleton Software Community");
		credentials.put("Fake", credential);		
	}
	
	
	public FromMapCredentialsRetrieverMock(SecurityCredentialsValidator<ApplicationCredentialsMock> credentialsValidator) {	
		super(credentialsValidator);
	}

	
	protected ApplicationCredentialsMock get(String token) {
		ApplicationCredentialsMock result = credentials.get(token);
		if (result == null) {
			throw new AccessDeniedException("Invalid Token");
		}
		return result;
	}
}
