package com.sklsft.commons.rest.security.tokens.encoder;

import org.sklsft.commons.crypto.accessors.RsaPrivateKeyAccessorMock;
import org.sklsft.commons.crypto.signature.RsaAlgorithms;
import org.sklsft.commons.crypto.signature.RsaSigner;
import org.sklsft.commons.rest.security.tokens.encoder.impl.PrivateRsaJwtEncoder;
import org.sklsft.commons.rest.security.tokens.jwt.BasicJwtBody;
import org.sklsft.commons.rest.security.tokens.jwt.BasicRsaJwtHeader;
import org.sklsft.commons.rest.security.tokens.jwt.JsonWebToken;

import com.fasterxml.jackson.databind.ObjectMapper;

public class BasicAesJwtEncoderTest {

	private static final PrivateRsaJwtEncoder<JsonWebToken<BasicRsaJwtHeader, BasicJwtBody>, BasicRsaJwtHeader, BasicJwtBody> encoder = new PrivateRsaJwtEncoder<>(new ObjectMapper(), BasicRsaJwtHeader.class, BasicJwtBody.class, new RsaSigner(new RsaPrivateKeyAccessorMock()), RsaAlgorithms.RS256.name(), "test");
	
	public void test() {
		BasicJwtBody body = new BasicJwtBody();
		body.setApplication("sklgen");
		body.setUser("nicolas.thibault@sklsft.org");
	}
}
