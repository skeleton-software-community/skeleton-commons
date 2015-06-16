package org.sklsft.commons.rest.security;

/**
 * An interface which implementation should provide an encryption key and a symmetric algorithm for token encryption
 * 
 * @author Nicolas Thibault
 *
 */
public interface TokensEncryptionParametersAccessor {
	
	byte[] getTokenEncryptionKey();
	
	String getTokenEncryptionSymmetricAlgorithm();

}
