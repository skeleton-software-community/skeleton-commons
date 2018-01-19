package com.sklsft.commons.rest.security.context;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sklsft.commons.api.exception.rights.AccessDeniedException;
import org.sklsft.commons.rest.security.context.SecurityContextHolder;
import org.sklsft.commons.rest.security.context.SecurityContextProvider;
import org.sklsft.commons.rest.security.context.impl.SecurityContextProviderImpl;
import org.sklsft.commons.rest.security.credentials.SecurityCredentialsRetriever;
import org.sklsft.commons.rest.security.credentials.impl.FromCryptedTokenCredentialsRetriever;

import com.sklsft.commons.rest.security.credentials.ApplicationCredentialsMock;
import com.sklsft.commons.rest.security.credentials.ApplicationSecurityCredentialsValidatorMock;
import com.sklsft.commons.rest.security.credentials.FromMapCredentialsRetrieverMock;
import com.sklsft.commons.rest.security.credentials.UserCredentialsMock;
import com.sklsft.commons.rest.security.credentials.UserSecurityCredentialsEncoderMock;
import com.sklsft.commons.rest.security.credentials.UserSecurityCredentialsValidatorMock;


public class SecurityContextProviderImplTest {

	private static SecurityCredentialsRetriever<UserCredentialsMock> userCredentialsRetriever;
	private static SecurityCredentialsRetriever<ApplicationCredentialsMock> applicationCredentialsRetriever;
	private static SecurityContextProvider provider;
	
	@BeforeClass
	public static void init() {
		userCredentialsRetriever = new FromCryptedTokenCredentialsRetriever<>(new UserSecurityCredentialsEncoderMock(), new UserSecurityCredentialsValidatorMock());
		applicationCredentialsRetriever = new FromMapCredentialsRetrieverMock(new ApplicationSecurityCredentialsValidatorMock());
		provider = new SecurityContextProviderImpl<>(applicationCredentialsRetriever, userCredentialsRetriever);
	}
	
	@After
	public void clear() {
		provider.clearSecurityContext();
	}
	
	@Test
	public void testProvideValidCredentials() {
		provider.provideApplicationSecurityContext("Sklgen");
		provider.provideUserSecurityContext("Nicolas$Thibault");
		
		ApplicationCredentialsMock applicationCredentials = (ApplicationCredentialsMock) SecurityContextHolder.getApplicationCredentials();
		Assert.assertTrue(applicationCredentials.getName().equals("Sklgen") && applicationCredentials.getEditor().equals("Skeleton Software Community"));
		
		UserCredentialsMock userCredentials = (UserCredentialsMock) SecurityContextHolder.getUserCredentials();
		Assert.assertTrue(userCredentials.getFirstName().equals("Nicolas") && userCredentials.getLastName().equals("Thibault"));	
	}
	
	@Test(expected=AccessDeniedException.class)
	public void testProvideBadUserCredentials() {
		provider.provideApplicationSecurityContext("Sklgen");
		provider.provideUserSecurityContext("Fake$Thibault");
	}
	
	@Test(expected=AccessDeniedException.class)
	public void testProvideBadApplicationCredentials() {
		provider.provideApplicationSecurityContext("Test");
	}
	
	@Test(expected=AccessDeniedException.class)
	public void testProvideInvalidApplicationCredentials() {
		provider.provideApplicationSecurityContext("Fake");
	}
}
