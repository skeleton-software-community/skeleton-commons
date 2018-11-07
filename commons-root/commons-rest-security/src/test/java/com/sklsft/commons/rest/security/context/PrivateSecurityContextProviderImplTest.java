package com.sklsft.commons.rest.security.context;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sklsft.commons.rest.security.context.SecurityContextHolder;
import org.sklsft.commons.rest.security.context.SecurityContextProvider;
import org.sklsft.commons.rest.security.context.impl.SecurityContextProviderImpl;
import org.sklsft.commons.rest.security.credentials.retriever.SecurityCredentialsRetriever;
import org.sklsft.commons.rest.security.credentials.retriever.impl.FromCryptedTokenCredentialsRetriever;
import org.sklsft.commons.rest.security.credentials.validator.SecurityCredentialsValidator;
import org.sklsft.commons.rest.security.exception.InvalidTokenException;

import com.sklsft.commons.rest.security.credentials.CredentialsMock;
import com.sklsft.commons.rest.security.credentials.encoder.UserSecurityCredentialsEncoderMock;
import com.sklsft.commons.rest.security.credentials.validator.UserSecurityCredentialsValidatorMock;


public class PrivateSecurityContextProviderImplTest {

	private static SecurityCredentialsRetriever<CredentialsMock> credentialsRetriever;
	private static SecurityCredentialsValidator<CredentialsMock> credentialsValidator;
	private static SecurityContextProvider provider;
	
	@BeforeClass
	public static void init() {
		credentialsRetriever = new FromCryptedTokenCredentialsRetriever<>(new UserSecurityCredentialsEncoderMock());
		credentialsValidator = new UserSecurityCredentialsValidatorMock();
		provider = new SecurityContextProviderImpl<>(credentialsRetriever, credentialsValidator);
	}
	
	@After
	public void clear() {
		SecurityContextHolder.unbindCredentials();
	}
	
	@Test
	public void testProvideValidCredentials() {
		provider.provideSecurityContext("Nicolas$Thibault");
		
		CredentialsMock userCredentials = (CredentialsMock) SecurityContextHolder.getUserCredentials();
		Assert.assertTrue(userCredentials.getUserFirstName().equals("Nicolas") && userCredentials.getUserLastName().equals("Thibault"));	
	}
	
	@Test(expected=InvalidTokenException.class)
	public void testProvideBadCredentials() {
		provider.provideSecurityContext("Fake$Thibault");
	}
}
