package com.sklsft.commons.rest.security.credentials.encoder;

import org.junit.Assert;
import org.junit.Test;

import com.sklsft.commons.rest.security.credentials.CredentialsMock;

public class UserSecurityCredentialsEncoderMockTest {

	private static UserSecurityCredentialsEncoderMock encoder = new UserSecurityCredentialsEncoderMock();
	
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
