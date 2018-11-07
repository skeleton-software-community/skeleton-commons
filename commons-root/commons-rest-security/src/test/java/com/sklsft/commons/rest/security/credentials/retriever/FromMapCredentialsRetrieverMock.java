package com.sklsft.commons.rest.security.credentials.retriever;

import java.util.HashMap;
import java.util.Map;

import org.sklsft.commons.rest.security.credentials.retriever.impl.FromMapCredentialsRetriever;
import org.sklsft.commons.rest.security.exception.InvalidTokenException;

import com.sklsft.commons.rest.security.credentials.CredentialsMock;

/**
 *
 *
 * @author Nicolas Thibault, Abdessalam El Jai, Alexandre Rupp
 */
public class FromMapCredentialsRetrieverMock extends FromMapCredentialsRetriever<CredentialsMock> {
	
	private static Map<String, CredentialsMock> credentials = new HashMap<>();
	
	static {
		CredentialsMock credential = new CredentialsMock();
		credential.setApplicationName("Sklgen");
		credential.setApplicationEditor("Skeleton Software Community");
		credentials.put("Sklgen", credential);
		
		credential = new CredentialsMock();
		credential.setApplicationName("Fake");
		credential.setApplicationEditor("Skeleton Software Community");
		credentials.put("Fake", credential);		
	}
	
	
	protected CredentialsMock get(String token) {
		CredentialsMock result = credentials.get(token);
		if (result == null) {
			throw new InvalidTokenException("Invalid Token");
		}
		return result;
	}
}
