package org.sklsft.commons.crypto.signature;

public enum RsaAlgorithms {
	
	RS256("SHA256withRSA"),
	RS512("SHA512withRSA");
	
	private RsaAlgorithms(String name) {
		this.name = name;
	}
	
	private String name;

	public String getFullName() {
		return name;
	}	
}
