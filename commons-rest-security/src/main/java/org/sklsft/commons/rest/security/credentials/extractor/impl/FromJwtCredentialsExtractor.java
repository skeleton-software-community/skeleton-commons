package org.sklsft.commons.rest.security.credentials.extractor.impl;

import org.sklsft.commons.rest.security.credentials.extractor.SecurityCredentialsExtractor;
import org.sklsft.commons.rest.security.tokens.jwt.JsonWebToken;

public abstract class FromJwtCredentialsExtractor<H, B, C> implements SecurityCredentialsExtractor<JsonWebToken<H, B>, C> {

	@Override
	public C getCredentials(JsonWebToken<H, B> token) {
		return extractFromBody(token.getBody());
	}

	protected abstract C extractFromBody(B body);

}
