package com.sklsft.commons.rest.security.context;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sklsft.commons.rest.security.context.SecurityContextHolder;
import org.sklsft.commons.rest.security.context.SecurityContextProvider;
import org.sklsft.commons.rest.security.context.impl.SecurityContextProviderImpl;
import org.sklsft.commons.rest.security.credentials.extractor.SecurityCredentialsExtractor;
import org.sklsft.commons.rest.security.credentials.validator.SecurityCredentialsValidator;
import org.sklsft.commons.rest.security.exception.InvalidTokenException;
import org.sklsft.commons.rest.security.tokens.encoder.TokenEncoder;
import org.sklsft.commons.rest.security.tokens.encoder.impl.PlainTextTokenEncoder;
import org.sklsft.commons.rest.security.tokens.verification.TokenVerifier;
import org.sklsft.commons.rest.security.tokens.verification.impl.UnsignedTokenVerifier;

import com.sklsft.commons.rest.security.credentials.extractor.FromMapCredentialsMockExtractor;
import com.sklsft.commons.rest.security.credentials.validator.ApplicationCredentialsMockValidator;
import com.sklsft.commons.rest.security.tokens.CredentialsMock;


public class AnonymousSecurityContextProviderImplTest {
	
	private static TokenEncoder<String> tokenEncoder = new PlainTextTokenEncoder();
	private static TokenVerifier<String> tokenVerifier = new UnsignedTokenVerifier<>();
	private static SecurityCredentialsExtractor<String, CredentialsMock> credentialsExtractor = new FromMapCredentialsMockExtractor();
	private static SecurityCredentialsValidator<CredentialsMock> credentialsValidator = new ApplicationCredentialsMockValidator();

	private static SecurityContextProvider provider;
	
	@BeforeClass
	public static void init() {
		provider = new SecurityContextProviderImpl<String, CredentialsMock>(tokenEncoder, tokenVerifier, credentialsExtractor, credentialsValidator);
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
