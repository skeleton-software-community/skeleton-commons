package org.sklsft.commons.rest.security.credentials.extractor.impl;

import org.sklsft.commons.rest.security.credentials.BasicCredentials;
import org.sklsft.commons.rest.security.tokens.jwt.BasicJwtBody;
import org.sklsft.commons.rest.security.tokens.jwt.BasicRsaJwtHeader;

public class FromBasicJwtCredentialsExtractor extends FromJwtCredentialsExtractor<BasicRsaJwtHeader, BasicJwtBody, BasicCredentials>{

	@Override
	protected BasicCredentials extractFromBody(BasicJwtBody body) {
		BasicCredentials result = new BasicCredentials();
		result.setApplication(body.getApplication());
		result.setUser(body.getUser());
		return result;
	}

}
