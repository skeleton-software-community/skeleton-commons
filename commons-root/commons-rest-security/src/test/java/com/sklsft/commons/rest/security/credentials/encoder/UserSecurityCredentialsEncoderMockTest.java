package com.sklsft.commons.rest.security.credentials.encoder;

import org.junit.Assert;
import org.junit.Test;

import com.sklsft.commons.rest.security.tokens.CredentialsMock;
import com.sklsft.commons.rest.security.tokens.encoder.CredentialsMockEncoder;

public class UserSecurityCredentialsEncoderMockTest {

	private static CredentialsMockEncoder encoder = new CredentialsMockEncoder();
	
	@Test
	public void testEncode() {
		CredentialsMock credentials = new CredentialsMock();
		credentials.setUserFirstName("Nicolas");
		credentials.setUserLastName("Thibault");
		
		String token = encoder.encode(credentials);
		System.out.println(token);
		
		CredentialsMock decoded = encoder.decode(token);
		Assert.assertEquals(credentials, decoded);
	}
}
