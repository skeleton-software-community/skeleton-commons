package com.sklsft.commons.rest.security.credentials;

import org.junit.Assert;
import org.junit.Test;

public class UserSecurityCredentialsEncoderMockTest {

	private static UserSecurityCredentialsEncoderMock encoder = new UserSecurityCredentialsEncoderMock();
	
	@Test
	public void testEncode() {
		UserCredentialsMock credentials = new UserCredentialsMock();
		credentials.setFirstName("Nicolas");
		credentials.setLastName("Thibault");
		
		String token = encoder.encode(credentials);
		System.out.println(token);
		
		UserCredentialsMock decoded = encoder.decode(token);
		Assert.assertEquals(credentials, decoded);
	}
}
