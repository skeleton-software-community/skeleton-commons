package com.sklsft.commons.rest.security.context;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sklsft.commons.rest.security.context.SecurityContextHolder;
import org.sklsft.commons.rest.security.context.SecurityContextProvider;
import org.sklsft.commons.rest.security.context.impl.FromCryptedTokenSecurityContextProvider;
import org.sklsft.commons.rest.security.credentials.validator.SecurityCredentialsValidator;
import org.sklsft.commons.rest.security.exception.InvalidTokenException;
import org.sklsft.commons.rest.security.tokens.encoder.TokenEncoder;

import com.sklsft.commons.rest.security.credentials.validator.UserCredentialsMockValidator;
import com.sklsft.commons.rest.security.tokens.CredentialsMock;
import com.sklsft.commons.rest.security.tokens.encoder.CredentialsMockEncoder;


public class PrivateSecurityContextProviderImplTest {

	private static TokenEncoder<CredentialsMock> tokenEncoder = new CredentialsMockEncoder();
	private static SecurityCredentialsValidator<CredentialsMock> credentialsValidator = new UserCredentialsMockValidator();

	private static SecurityContextProvider provider;
	
	@BeforeClass
	public static void init() {
		provider = new FromCryptedTokenSecurityContextProvider<CredentialsMock>(tokenEncoder, credentialsValidator);
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
