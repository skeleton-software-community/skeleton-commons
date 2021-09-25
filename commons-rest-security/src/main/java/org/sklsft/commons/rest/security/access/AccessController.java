package org.sklsft.commons.rest.security.access;

import org.sklsft.commons.rest.security.context.SecurityContextProvider;
import org.sklsft.commons.rest.security.tokens.extraction.TokenExtractionMode;
import org.sklsft.commons.rest.security.tokens.extraction.impl.TokenExtractorFactory;

/**
 * 
 * This class will :
 * <li>Extract the token with @{link TokenExtractor}
 * <li>Give a security context with the {@link SecurityContextProvider}
 * 
 * @author Nicolas Thibault, Abdessalam El Jai, Alexandre Rupp
 *
 */
public class AccessController {

	private SecurityContextProvider anonymousSecurityContextProvider;
	private SecurityContextProvider privateSecurityContextProvider;
	private String anonymousTokenName;
	private String privateTokenName;


	public AccessController(SecurityContextProvider anonymousSecurityContextProvider,
			SecurityContextProvider privateSecurityContextProvider, String anymousTokenName, String privateTokenName) {
		super();
		this.anonymousSecurityContextProvider = anonymousSecurityContextProvider;
		this.privateSecurityContextProvider = privateSecurityContextProvider;
		this.anonymousTokenName = anymousTokenName;
		this.privateTokenName = privateTokenName;
	}


	public void handshake(AccessControlType accessControlType, TokenExtractionMode tokenExtractionMode) {

		if (!accessControlType.equals(AccessControlType.PUBLIC)) {
			if (accessControlType.equals(AccessControlType.ANONYMOUS)) {
				String token = extractToken(anonymousTokenName, tokenExtractionMode);
				anonymousSecurityContextProvider.provideSecurityContext(token);
			} else {
				String token = extractToken(privateTokenName, tokenExtractionMode);
				privateSecurityContextProvider.provideSecurityContext(token);
			}
		}
	}


	private String extractToken(String key, TokenExtractionMode tokenExtractionMode) {
		String result = TokenExtractorFactory.getTokenExtractor(tokenExtractionMode).extractToken(key);
		return result;
	}
}
