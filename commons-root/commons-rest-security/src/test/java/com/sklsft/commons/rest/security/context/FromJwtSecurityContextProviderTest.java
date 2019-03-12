package com.sklsft.commons.rest.security.context;

import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;
import org.junit.Test;
import org.sklsft.commons.crypto.signature.RsaAlgorithms;
import org.sklsft.commons.crypto.signature.RsaSignatureVerifier;
import org.sklsft.commons.crypto.signature.RsaSigner;
import org.sklsft.commons.rest.security.context.SecurityContextHolder;
import org.sklsft.commons.rest.security.context.impl.FromJwtSecurityContextProvider;
import org.sklsft.commons.rest.security.credentials.BasicCredentials;
import org.sklsft.commons.rest.security.exception.InvalidTokenException;
import org.sklsft.commons.rest.security.tokens.encoder.impl.PrivateRsaJwtEncoder;
import org.sklsft.commons.rest.security.tokens.encoder.impl.PublicJwtDecoder;
import org.sklsft.commons.rest.security.tokens.jwt.JsonWebToken;
import org.sklsft.commons.rest.security.tokens.verification.impl.RsaJwtVerifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sklsft.commons.rest.security.credentials.extractor.FromBasicJwtCredentialsExtractor;
import com.sklsft.commons.rest.security.credentials.validator.BasicCredentialsValidatorMock;
import com.sklsft.commons.rest.security.crypto.RsaPrivateKeyAccessorMock;
import com.sklsft.commons.rest.security.crypto.RsaPublicKeyAccessorMock;
import com.sklsft.commons.rest.security.tokens.jwt.BasicJwtBody;
import com.sklsft.commons.rest.security.tokens.jwt.BasicRsaJwtHeader;
import com.sklsft.commons.rest.security.tokens.verification.RsaJwtVerifierTest;

public class FromJwtSecurityContextProviderTest {

	private static final Logger logger = LoggerFactory.getLogger(RsaJwtVerifierTest.class);

	private static final PrivateRsaJwtEncoder<BasicRsaJwtHeader, BasicJwtBody> encoder = new PrivateRsaJwtEncoder<>(new ObjectMapper(), BasicRsaJwtHeader.class, BasicJwtBody.class, new RsaSigner(new RsaPrivateKeyAccessorMock()), RsaAlgorithms.RS256.name(), "test");
	
	private static final PublicJwtDecoder<BasicRsaJwtHeader, BasicJwtBody> decoder = new PublicJwtDecoder<>(new ObjectMapper(), BasicRsaJwtHeader.class, BasicJwtBody.class);
	private static final RsaJwtVerifier<BasicRsaJwtHeader, BasicJwtBody> verifier = new RsaJwtVerifier<>(new RsaSignatureVerifier(new RsaPublicKeyAccessorMock()));
	private static final FromJwtSecurityContextProvider<BasicRsaJwtHeader, BasicJwtBody, BasicCredentials> provider;
	
	static {
		provider = new FromJwtSecurityContextProvider<BasicRsaJwtHeader, BasicJwtBody, BasicCredentials>(new ObjectMapper(), BasicRsaJwtHeader.class, BasicJwtBody.class, verifier, new FromBasicJwtCredentialsExtractor(), new BasicCredentialsValidatorMock());
	}
	
	@Test
	public void testGoodToken() {
		
		BasicJwtBody body = new BasicJwtBody();
		body.setApplication("sklgen");
		body.setUser("nicolas.thibault@sklsft.org");
		
		BasicRsaJwtHeader header = new BasicRsaJwtHeader();
		header.setAlgorithm(RsaAlgorithms.RS256.name());
		header.setPublicKeyId("test");
		
		JsonWebToken<BasicRsaJwtHeader, BasicJwtBody> jwt = new JsonWebToken<>();
		jwt.setHeader(header);
		jwt.setBody(body);
		
		String token = encoder.encode(jwt);
		
		provider.provideSecurityContext(token);
		
		BasicCredentials userCredentials = (BasicCredentials) SecurityContextHolder.getCredentials();
		Assert.assertTrue(userCredentials.getUser().equals("nicolas.thibault@sklsft.org") && userCredentials.getApplication().equals("sklgen"));
		
	}
	
	
	@Test(expected=InvalidTokenException.class)
	public void testInvalidCredentials() {
		
		BasicJwtBody body = new BasicJwtBody();
		body.setApplication("sklgen");
		body.setUser("nicolas.thibault@sklsft.com");
		
		BasicRsaJwtHeader header = new BasicRsaJwtHeader();
		header.setAlgorithm(RsaAlgorithms.RS256.name());
		header.setPublicKeyId("test");
		
		JsonWebToken<BasicRsaJwtHeader, BasicJwtBody> jwt = new JsonWebToken<>();
		jwt.setHeader(header);
		jwt.setBody(body);
		
		String token = encoder.encode(jwt);
		
		try {
			provider.provideSecurityContext(token);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		
	}
	
	
	@Test(expected=InvalidTokenException.class)
	public void testModifiedBody() throws JsonProcessingException {
		
		BasicJwtBody body = new BasicJwtBody();
		body.setApplication("sklgen");
		body.setUser("nicolas.thibault@sklsft.org");
		
		BasicRsaJwtHeader header = new BasicRsaJwtHeader();
		header.setAlgorithm(RsaAlgorithms.RS256.name());
		header.setPublicKeyId("test");
		
		JsonWebToken<BasicRsaJwtHeader, BasicJwtBody> jwt = new JsonWebToken<>();
		jwt.setHeader(header);
		jwt.setBody(body);
		
		String token = encoder.encode(jwt);
		
		String[] parts = token.split("\\.");
		
		BasicJwtBody modifiedBody = new BasicJwtBody();
		modifiedBody.setApplication("sklgen");
		modifiedBody.setUser("nicolas.thibault@sklsft.com");
		String wrongPart = Base64.encodeBase64URLSafeString(new ObjectMapper().writeValueAsBytes(modifiedBody));
		
		String fake = parts[0] + "." + wrongPart + "." + parts[2];
		
		try {
			provider.provideSecurityContext(fake);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		
	}
}
