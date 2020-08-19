package org.sklsft.commons.rest.security.tokens.jwt;

public class BasicRsaJsonWebToken extends JsonWebToken<BasicRsaJwtHeader, BasicJwtBody> {

	private static final long serialVersionUID = 1L;
	
	public BasicRsaJsonWebToken(BasicRsaJwtHeader header, BasicJwtBody body) {
		super(header, body);
	}
}
