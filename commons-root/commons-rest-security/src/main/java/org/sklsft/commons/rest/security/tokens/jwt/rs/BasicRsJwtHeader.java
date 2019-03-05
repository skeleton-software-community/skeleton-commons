package org.sklsft.commons.rest.security.tokens.jwt.rs;

import java.io.Serializable;

public class BasicRsJwtHeader implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/*
	 * properties
	 */
	private String algorithm;
	private String publicKeyId;
	
	/*
	 * getters and setters
	 */
	public String getAlgorithm() {
		return algorithm;
	}
	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}
	public String getPublicKeyId() {
		return publicKeyId;
	}
	public void setPublicKeyId(String publicKeyId) {
		this.publicKeyId = publicKeyId;
	}
}
