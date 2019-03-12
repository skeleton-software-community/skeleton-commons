package com.sklsft.commons.rest.security.tokens.jwt;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BasicRsaJwtHeader implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/*
	 * properties
	 */
	@JsonProperty("alg")
	private String algorithm;
	@JsonProperty("typ")
	private String type = "JWT";
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPublicKeyId() {
		return publicKeyId;
	}
	public void setPublicKeyId(String publicKeyId) {
		this.publicKeyId = publicKeyId;
	}
}
