package org.sklsft.commons.rest.security.tokens.jwt;

public class BasicRsaJsonWebToken extends JsonWebToken<RsaJwtHeader, BasicJwtBody> {

	private static final long serialVersionUID = 1L;
	
	public BasicRsaJsonWebToken() {
		super();
	}
	
	public BasicRsaJsonWebToken(RsaJwtHeader header, BasicJwtBody body) {
		super(header, body);
	}
}
