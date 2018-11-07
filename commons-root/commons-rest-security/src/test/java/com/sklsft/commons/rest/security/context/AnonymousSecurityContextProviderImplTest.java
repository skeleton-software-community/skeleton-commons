package com.sklsft.commons.rest.security.context;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sklsft.commons.rest.security.context.SecurityContextHolder;
import org.sklsft.commons.rest.security.context.SecurityContextProvider;
import org.sklsft.commons.rest.security.context.impl.SecurityContextProviderImpl;
import org.sklsft.commons.rest.security.credentials.retriever.SecurityCredentialsRetriever;
import org.sklsft.commons.rest.security.credentials.validator.SecurityCredentialsValidator;
import org.sklsft.commons.rest.security.exception.InvalidTokenException;

import com.sklsft.commons.rest.security.credentials.CredentialsMock;
import com.sklsft.commons.rest.security.credentials.retriever.FromMapCredentialsRetrieverMock;
import com.sklsft.commons.rest.security.credentials.validator.ApplicationSecurityCredentialsValidatorMock;


public class AnonymousSecurityContextProviderImplTest {

	private static SecurityCredentialsRetriever<CredentialsMock> credentialsRetriever;
	private static SecurityCredentialsValidator<CredentialsMock> credentialsValidator;
	private static SecurityContextProvider provider;
	
	@BeforeClass
	public static void init() {
		credentialsRetriever = new FromMapCredentialsRetrieverMock();
		credentialsValidator = new ApplicationSecurityCredentialsValidatorMock();
		provider = new SecurityContextProviderImpl<>(credentialsRetriever, credentialsValidator);
	}
	
	@After
	public void clear() {
		SecurityContextHolder.unbindCredentials();
	}
	
	@Test
	public void testProvideValidCredentials() {
		provider.provideSecurityContext("Sklgen");
		
		CredentialsMock credentials = (CredentialsMock) SecurityContextHolder.getUserCredentials();
		Assert.assertTrue(credentials.getApplicationName().equals("Sklgen") && credentials.getApplicationEditor().equals("Skeleton Software Community"));	
	}
	
	@Test(expected=InvalidTokenException.class)
	public void testProvideBadCredentials() {
		provider.provideSecurityContext("Fake");
	}
}
