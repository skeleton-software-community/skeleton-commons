package com.sklsft.commons.rest.security.context;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sklsft.commons.rest.security.context.SecurityContextHolder;
import org.sklsft.commons.rest.security.context.SecurityContextProvider;
import org.sklsft.commons.rest.security.context.impl.SecurityContextProviderImpl;
import org.sklsft.commons.rest.security.credentials.extractor.SecurityCredentialsExtractor;
import org.sklsft.commons.rest.security.credentials.extractor.impl.FromUnsignedTokenCredentialsExtractor;
import org.sklsft.commons.rest.security.credentials.validator.SecurityCredentialsValidator;
import org.sklsft.commons.rest.security.exception.InvalidTokenException;
import org.sklsft.commons.rest.security.tokens.encoder.TokenEncoder;
import org.sklsft.commons.rest.security.tokens.verification.TokenVerifier;
import org.sklsft.commons.rest.security.tokens.verification.impl.UnsignedTokenVerifier;

import com.sklsft.commons.rest.security.credentials.validator.UserCredentialsMockValidator;
import com.sklsft.commons.rest.security.tokens.CredentialsMock;
import com.sklsft.commons.rest.security.tokens.encoder.CredentialsMockEncoder;


public class PrivateSecurityContextProviderImplTest {

	private static TokenEncoder<CredentialsMock> tokenEncoder = new CredentialsMockEncoder();
	private static TokenVerifier<CredentialsMock> tokenVerifier = new UnsignedTokenVerifier<>();
	private static SecurityCredentialsExtractor<CredentialsMock, CredentialsMock> credentialsExtractor = new FromUnsignedTokenCredentialsExtractor<>();
	private static SecurityCredentialsValidator<CredentialsMock> credentialsValidator = new UserCredentialsMockValidator();

	private static SecurityContextProvider provider;
	
	@BeforeClass
	public static void init() {
		provider = new SecurityContextProviderImpl<CredentialsMock, CredentialsMock>(tokenEncoder, tokenVerifier, credentialsExtractor, credentialsValidator);
	}
	
	@After
	public void clear() {
		SecurityContextHolder.unbindCredentials();
	}
	
	@Test
	public void testProvideValidCredentials() {
		provider.provideSecurityContext("Nicolas$Thibault");
		
		CredentialsMock userCredentials = (CredentialsMock) SecurityContextHolder.getCredentials();
		Assert.assertTrue(userCredentials.getUserFirstName().equals("Nicolas") && userCredentials.getUserLastName().equals("Thibault"));	
	}
	
	@Test(expected=InvalidTokenException.class)
	public void testProvideBadCredentials() {
		provider.provideSecurityContext("Fake$Thibault");
	}
}
