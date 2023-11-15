package com.sklsft.commons.rest.security.context;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sklsft.commons.rest.security.context.SecurityContextHolder;
import org.sklsft.commons.rest.security.context.SecurityContextProvider;
import org.sklsft.commons.rest.security.credentials.validator.SecurityContextValidator;
import org.sklsft.commons.rest.security.exception.InvalidTokenException;

import com.sklsft.commons.rest.security.credentials.validator.ApplicationContextValidatorMock;
import com.sklsft.commons.rest.security.tokens.SecurityContextMock;


public class FromKeySecurityContextProviderTest {
	
	private static SecurityContextValidator<SecurityContextMock> validator = new ApplicationContextValidatorMock();

	private static SecurityContextProvider provider;
	
	@BeforeClass
	public static void init() {
		provider = new FromMapSecurityContextProviderMock(validator);
	}
	
	@After
	public void clear() {
		SecurityContextHolder.unbindContext();
	}
	
	@Test
	public void testProvideValidCredentials() {
		provider.provideSecurityContext("sklgen");
		
		SecurityContextMock context = (SecurityContextMock) SecurityContextHolder.getContext();
		Assert.assertTrue(context.getApplication().equals("sklgen"));
	}
	
	@Test(expected=InvalidTokenException.class)
	public void testProvideBadCredentials() {
		provider.provideSecurityContext("Fake");
	}
}
