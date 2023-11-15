package com.sklsft.commons.rest.security.tokens.encoder;

import org.junit.Assert;
import org.junit.Test;
import org.sklsft.commons.crypto.signature.RsaAlgorithms;
import org.sklsft.commons.crypto.signature.RsaSigner;
import org.sklsft.commons.rest.security.tokens.encoder.impl.BasicRsaJwtDecoder;
import org.sklsft.commons.rest.security.tokens.encoder.impl.BasicRsaJwtEncoder;
import org.sklsft.commons.rest.security.tokens.jwt.BasicJwtBody;
import org.sklsft.commons.rest.security.tokens.jwt.BasicRsaJsonWebToken;
import org.sklsft.commons.rest.security.tokens.jwt.JsonWebToken;
import org.sklsft.commons.rest.security.tokens.jwt.RsaJwtHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sklsft.commons.rest.security.crypto.RsaPrivateKeyAccessorMock;

public class BasicRsaJwtEncoderTest {
	
	private static final Logger logger = LoggerFactory.getLogger(BasicRsaJwtEncoderTest.class);

	private static final BasicRsaJwtEncoder encoder = new BasicRsaJwtEncoder(new ObjectMapper(), new RsaSigner(new RsaPrivateKeyAccessorMock()), RsaAlgorithms.RS256.name(), "test");
	
	private static final BasicRsaJwtDecoder decoder = new BasicRsaJwtDecoder(new ObjectMapper());
	
	@Test
	public void test() {
		
		BasicJwtBody body = new BasicJwtBody();
		body.setApplication("sklgen");
		body.setUser("nicolas.thibault@sklsft.org");
		
		RsaJwtHeader header = new RsaJwtHeader(RsaAlgorithms.RS256, "test");
		
		BasicRsaJsonWebToken jwt = new BasicRsaJsonWebToken(header, body);
		
		String encoded = encoder.encode(jwt);
		logger.debug(encoded);
		
		JsonWebToken<RsaJwtHeader, BasicJwtBody> decoded = decoder.decode(encoded);
		logger.debug(decoded.getHeader().getAlgorithm());
		logger.debug(decoded.getHeader().getPublicKeyId());
		logger.debug(decoded.getBody().getApplication());
		logger.debug(decoded.getBody().getUser());
		
		Assert.assertTrue(decoded.getHeader().getAlgorithm().equals(RsaAlgorithms.RS256.name()));
		Assert.assertTrue(decoded.getHeader().getPublicKeyId().equals("test"));
		Assert.assertTrue(decoded.getBody().getApplication().equals("sklgen"));
		Assert.assertTrue(decoded.getBody().getUser().equals("nicolas.thibault@sklsft.org"));
	}
}
