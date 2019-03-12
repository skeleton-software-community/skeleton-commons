package com.sklsft.commons.rest.security.context;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sklsft.commons.rest.security.context.SecurityContextHolder;
import org.sklsft.commons.rest.security.context.SecurityContextProvider;
import org.sklsft.commons.rest.security.credentials.BasicCredentials;
import org.sklsft.commons.rest.security.credentials.validator.SecurityCredentialsValidator;
import org.sklsft.commons.rest.security.exception.InvalidTokenException;

import com.sklsft.commons.rest.security.credentials.validator.ApplicationCredentialsValidatorMock;


public class FromKeySecurityContextProviderTest {
	
	private static SecurityCredentialsValidator<BasicCredentials> credentialsValidator = new ApplicationCredentialsValidatorMock();

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
		provider.provideSecurityContext("sklgen");
		
		BasicCredentials credentials = (BasicCredentials) SecurityContextHolder.getCredentials();
		Assert.assertTrue(credentials.getApplication().equals("sklgen"));
	}
	
	@Test(expected=InvalidTokenException.class)
	public void testProvideBadCredentials() {
		provider.provideSecurityContext("Fake");
	}
}
