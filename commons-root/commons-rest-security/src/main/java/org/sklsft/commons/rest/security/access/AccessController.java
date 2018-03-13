package org.sklsft.commons.rest.security.access;

import org.sklsft.commons.rest.security.context.SecurityContextProvider;
import org.sklsft.commons.rest.security.tokens.TokenExtractionMode;
import org.sklsft.commons.rest.security.tokens.impl.TokenExtractorFactory;

/**
 * 
 * This class will :
 * <li>Extract the token and the application secret key with @{link TokenExtractor}
 * <li>Give a security context with the {@link SecurityContextProvider}
 * 
 * @author Nicolas Thibault, Abdessalam El Jai, Alexandre Rupp
 *
 */
public class AccessController {

	private SecurityContextProvider securityContextProvider;
	private TokenExtractorFactory tokenExtractorFactory;
	
	private String applicationTokenName;
	private String userTokenName;
	
	
	public AccessController(SecurityContextProvider securityContextProvider,
			String applicationTokenName, String userTokenName) {
		super();
		this.tokenExtractorFactory = new TokenExtractorFactory();
		this.securityContextProvider = securityContextProvider;		
		this.applicationTokenName = applicationTokenName;
		this.userTokenName = userTokenName;
	}


	public void handshake(AccessControlType accessControlType, TokenExtractionMode tokenExtractionMode) {

		if (!accessControlType.equals(AccessControlType.PUBLIC)) {
			String secretKey = extractToken(applicationTokenName, tokenExtractionMode);
			securityContextProvider.provideApplicationSecurityContext(secretKey);
		}

		if (accessControlType.equals(AccessControlType.PRIVATE)) {
			String token = extractToken(userTokenName, tokenExtractionMode);
			securityContextProvider.provideUserSecurityContext(token);
		}			
	}


	private String extractToken(String key, TokenExtractionMode tokenExtractionMode) {
		String result = tokenExtractorFactory.getTokenExtractor(tokenExtractionMode).extractToken(key);
		
		return result;
	}
}
