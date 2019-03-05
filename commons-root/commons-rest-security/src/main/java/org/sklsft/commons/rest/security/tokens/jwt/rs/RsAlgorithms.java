package org.sklsft.commons.rest.security.tokens.jwt.rs;

public enum RsAlgorithms {
	
	RS256("SHA256withRSA"),
	RS512("SHA512withRSA");
	
	private RsAlgorithms(String name) {
		this.name = name;
	}
	
	private String name;

	public String getName() {
		return name;
	}	
}
