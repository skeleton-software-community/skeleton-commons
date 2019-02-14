package com.sklsft.commons.rest.security.context;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sklsft.commons.rest.security.context.SecurityContextHolder;
import org.sklsft.commons.rest.security.context.SecurityContextProvider;
import org.sklsft.commons.rest.security.credentials.validator.SecurityCredentialsValidator;
import org.sklsft.commons.rest.security.exception.InvalidTokenException;

import com.sklsft.commons.rest.security.credentials.validator.ApplicationCredentialsMockValidator;
import com.sklsft.commons.rest.security.tokens.CredentialsMock;


public class AnonymousSecurityContextProviderImplTest {
	
	private static SecurityCredentialsValidator<CredentialsMock> credentialsValidator = new ApplicationCredentialsMockValidator();

	private static SecurityContextProvider provider;
	
	@BeforeClass
	public static void init() {
		provider = new FromMapSecurityContextProviderMock(credentialsValidator);
	}
	
	@After
	public void clear() {
		SecurityContextHolder.unbindCredentials();
	}
	
	@Test
	public void testProvideValidCredentials() {
		provider.provideSecurityContext("Sklgen");
		
		CredentialsMock credentials = (CredentialsMock) SecurityContextHolder.getCredentials();
		Assert.assertTrue(credentials.getApplicationName().equals("Sklgen") && credentials.getApplicationEditor().equals("Skeleton Software Community"));	
	}
	
	@Test(expected=InvalidTokenException.class)
	public void testProvideBadCredentials() {
		provider.provideSecurityContext("Fake");
	}
}
