package com.sklsft.commons.rest.security.context;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sklsft.commons.rest.security.context.SecurityContextHolder;
import org.sklsft.commons.rest.security.context.SecurityContextProvider;
import org.sklsft.commons.rest.security.context.impl.FromCryptedTokenSecurityContextProvider;
import org.sklsft.commons.rest.security.credentials.validator.SecurityContextValidator;
import org.sklsft.commons.rest.security.exception.InvalidTokenException;
import org.sklsft.commons.rest.security.tokens.encoder.TokenDecoder;

import com.sklsft.commons.rest.security.credentials.validator.UserContextValidatorMock;
import com.sklsft.commons.rest.security.tokens.SecurityContextMock;
import com.sklsft.commons.rest.security.tokens.encoder.DecoderMock;


public class FromCryptedTokenSecurityContextProviderTest {

	private static TokenDecoder<SecurityContextMock> tokenEncoder = new DecoderMock();
	private static SecurityContextValidator<SecurityContextMock> validator = new UserContextValidatorMock();

	private static SecurityContextProvider provider;
	
	@BeforeClass
	public static void init() {
		provider = new FromCryptedTokenSecurityContextProvider<SecurityContextMock>(tokenEncoder, validator);
	}
	
	@After
	public void clear() {
		SecurityContextHolder.unbindContext();
	}
	
	@Test
	public void testProvideValidCredentials() {
		provider.provideSecurityContext("sklgen$nicolas.thibault@sklsft.org");
		
		SecurityContextMock userCredentials = (SecurityContextMock) SecurityContextHolder.getContext();
		Assert.assertTrue(userCredentials.getUser().equals("nicolas.thibault@sklsft.org") && userCredentials.getApplication().equals("sklgen"));
	}
	
	@Test(expected=InvalidTokenException.class)
	public void testProvideBadCredentials() {
		provider.provideSecurityContext("Fake$Thibault");
	}
}
