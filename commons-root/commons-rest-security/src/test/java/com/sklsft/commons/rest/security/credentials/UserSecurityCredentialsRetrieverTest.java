package com.sklsft.commons.rest.security.credentials;

import org.junit.BeforeClass;
import org.junit.Test;
import org.sklsft.commons.api.exception.rights.AccessDeniedException;
import org.sklsft.commons.rest.security.credentials.SecurityCredentialsRetriever;
import org.sklsft.commons.rest.security.credentials.impl.FromCryptedTokenCredentialsRetriever;

public class UserSecurityCredentialsRetrieverTest {
	
	private static SecurityCredentialsRetriever<UserCredentialsMock> userSecurityCredentialsRetriever;
	
	@BeforeClass
	public static void init() {
		userSecurityCredentialsRetriever = new FromCryptedTokenCredentialsRetriever<>(new UserSecurityCredentialsEncoderMock(), new UserSecurityCredentialsValidatorMock());
	}
	
	@Test
	public void testGoodCredentials() {
		UserCredentialsMock credentials = userSecurityCredentialsRetriever.retrieveCredentials("Nicolas$Thibault");
		System.out.println(credentials);
	}
	
	@Test(expected = AccessDeniedException.class)
	public void testBadCredentials() {
		UserCredentialsMock credentials = userSecurityCredentialsRetriever.retrieveCredentials("Test$Test");
	}

}
