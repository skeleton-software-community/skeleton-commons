package com.sklsft.commons.rest.security.credentials.retriever;

import org.junit.BeforeClass;
import org.junit.Test;
import org.sklsft.commons.rest.security.credentials.retriever.SecurityCredentialsRetriever;
import org.sklsft.commons.rest.security.credentials.retriever.impl.FromCryptedTokenCredentialsRetriever;
import org.sklsft.commons.rest.security.exception.InvalidTokenException;

import com.sklsft.commons.rest.security.credentials.CredentialsMock;
import com.sklsft.commons.rest.security.credentials.encoder.UserSecurityCredentialsEncoderMock;

public class UserSecurityCredentialsRetrieverTest {
	
	private static SecurityCredentialsRetriever<CredentialsMock> credentialsRetriever;
	
	@BeforeClass
	public static void init() {
		credentialsRetriever = new FromCryptedTokenCredentialsRetriever<>(new UserSecurityCredentialsEncoderMock());
	}
	
	@Test
	public void testGoodCredentials() {
		CredentialsMock credentials =credentialsRetriever.retrieveCredentials("Nicolas$Thibault");
		System.out.println(credentials);
	}
	
	@Test(expected = InvalidTokenException.class)
	public void testBadCredentials() {
		credentialsRetriever.retrieveCredentials("Test");
	}

}
