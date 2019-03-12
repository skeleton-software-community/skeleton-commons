package com.sklsft.commons.rest.security.tokens.verification;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.sklsft.commons.crypto.signature.RsaAlgorithms;
import org.sklsft.commons.crypto.signature.RsaSignatureVerifier;
import org.sklsft.commons.crypto.signature.RsaSigner;
import org.sklsft.commons.rest.security.exception.InvalidTokenException;
import org.sklsft.commons.rest.security.tokens.encoder.impl.PrivateRsaJwtEncoder;
import org.sklsft.commons.rest.security.tokens.encoder.impl.PublicJwtDecoder;
import org.sklsft.commons.rest.security.tokens.jwt.BasicJwtBody;
import org.sklsft.commons.rest.security.tokens.jwt.BasicRsaJwtHeader;
import org.sklsft.commons.rest.security.tokens.jwt.JsonWebToken;
import org.sklsft.commons.rest.security.tokens.verification.impl.RsaJwtVerifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sklsft.commons.rest.security.crypto.RsaPrivateKeyAccessorMock;
import com.sklsft.commons.rest.security.crypto.RsaPublicKeyAccessorMock;

public class RsaJwtVerifierTest {
	
	private static final Logger logger = LoggerFactory.getLogger(RsaJwtVerifierTest.class);

	private static final PrivateRsaJwtEncoder<BasicRsaJwtHeader, BasicJwtBody> encoder = new PrivateRsaJwtEncoder<>(new ObjectMapper(), BasicRsaJwtHeader.class, BasicJwtBody.class, new RsaSigner(new RsaPrivateKeyAccessorMock()), RsaAlgorithms.RS256.name(), "test");
	
	private static final PublicJwtDecoder<BasicRsaJwtHeader, BasicJwtBody> decoder = new PublicJwtDecoder<>(new ObjectMapper(), BasicRsaJwtHeader.class, BasicJwtBody.class);
	
	private static final RsaJwtVerifier<BasicRsaJwtHeader, BasicJwtBody> verifier = new RsaJwtVerifier<>(new RsaSignatureVerifier(new RsaPublicKeyAccessorMock()));
	
	@Test
	public void testGoodSignature() {
		
		BasicJwtBody body = new BasicJwtBody();
		body.setApplication("sklgen");
		body.setUser("nicolas.thibault@sklsft.org");
		
		BasicRsaJwtHeader header = new BasicRsaJwtHeader();
		header.setAlgorithm(RsaAlgorithms.RS256.name());
		header.setPublicKeyId("test");
		
		JsonWebToken<BasicRsaJwtHeader, BasicJwtBody> jwt = new JsonWebToken<>();
		jwt.setHeader(header);
		jwt.setBody(body);
		
		String encoded = encoder.encode(jwt);
		
		JsonWebToken<BasicRsaJwtHeader, BasicJwtBody> decoded = decoder.decode(encoded);
		
		verifier.verifyToken(decoded);
		
	}
	
	
	@Test(expected=InvalidTokenException.class)
	public void testBadSignature() {
		
		BasicJwtBody body = new BasicJwtBody();
		body.setApplication("sklgen");
		body.setUser("nicolas.thibault@sklsft.org");
		
		BasicRsaJwtHeader header = new BasicRsaJwtHeader();
		header.setAlgorithm(RsaAlgorithms.RS256.name());
		header.setPublicKeyId("test");
		
		JsonWebToken<BasicRsaJwtHeader, BasicJwtBody> jwt = new JsonWebToken<>();
		jwt.setHeader(header);
		jwt.setBody(body);
		
		String encoded = encoder.encode(jwt);
		encoded = encoded + "A";
		
		JsonWebToken<BasicRsaJwtHeader, BasicJwtBody> decoded = decoder.decode(encoded);
		
		try {
			verifier.verifyToken(decoded);
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
		
		String encoded = encoder.encode(jwt);
		
		String[] parts = encoded.split("\\.");
		
		BasicJwtBody modifiedBody = new BasicJwtBody();
		modifiedBody.setApplication("sklgen");
		modifiedBody.setUser("nicolas.thibault@sklsft.com");
		String wrongPart = Base64.encodeBase64URLSafeString(new ObjectMapper().writeValueAsBytes(modifiedBody));
		
		String fake = parts[0] + "." + wrongPart + "." + parts[2];
		
		JsonWebToken<BasicRsaJwtHeader, BasicJwtBody> decoded = decoder.decode(fake);
		
		try {
			verifier.verifyToken(decoded);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		
	}
}
