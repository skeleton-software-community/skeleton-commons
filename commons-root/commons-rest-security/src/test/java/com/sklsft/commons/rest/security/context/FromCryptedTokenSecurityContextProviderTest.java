package com.sklsft.commons.rest.security.context;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sklsft.commons.rest.security.context.SecurityContextHolder;
import org.sklsft.commons.rest.security.context.SecurityContextProvider;
import org.sklsft.commons.rest.security.context.impl.FromCryptedTokenSecurityContextProvider;
import org.sklsft.commons.rest.security.credentials.BasicCredentials;
import org.sklsft.commons.rest.security.credentials.validator.SecurityCredentialsValidator;
import org.sklsft.commons.rest.security.exception.InvalidTokenException;
import org.sklsft.commons.rest.security.tokens.encoder.TokenEncoder;

import com.sklsft.commons.rest.security.credentials.validator.UserCredentialsValidatorMock;
import com.sklsft.commons.rest.security.tokens.encoder.BasicCredentialsEncoderMock;
import com.sklsft.commons.rest.security.tokens.jwt.BasicJwtBody;


public class FromCryptedTokenSecurityContextProviderTest {

	private static TokenEncoder<BasicCredentials> tokenEncoder = new BasicCredentialsEncoderMock();
	private static SecurityCredentialsValidator<BasicCredentials> credentialsValidator = new UserCredentialsValidatorMock();

	private static SecurityContextProvider provider;
	
	@BeforeClass
	public static void init() {
		provider = new FromCryptedTokenSecurityContextProvider<BasicCredentials>(tokenEncoder, credentialsValidator);
	}
	
	@After
	public void clear() {
		SecurityContextHolder.unbindCredentials();
	}
	
	@Test
	public void testProvideValidCredentials() {
		provider.provideSecurityContext("sklgen$nicolas.thibault@sklsft.org");
		
		BasicCredentials userCredentials = (BasicCredentials) SecurityContextHolder.getCredentials();
		Assert.assertTrue(userCredentials.getUser().equals("nicolas.thibault@sklsft.org") && userCredentials.getApplication().equals("sklgen"));
	}
	
	@Test(expected=InvalidTokenException.class)
	public void testProvideBadCredentials() {
		provider.provideSecurityContext("Fake$Thibault");
	}
}
