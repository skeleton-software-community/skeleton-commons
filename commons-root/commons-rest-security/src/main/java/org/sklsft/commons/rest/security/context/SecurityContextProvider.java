package org.sklsft.commons.rest.security.context;

public interface SecurityContextProvider {

	public void provideUserSecurityContext(String token);

	public void provideApplicationSecurityContext(String secretKey);

	public void clearSecurityContext();
}
