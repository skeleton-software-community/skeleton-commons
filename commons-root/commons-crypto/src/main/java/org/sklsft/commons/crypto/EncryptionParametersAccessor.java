package org.sklsft.commons.crypto;

/**
 * An interface which implementation should provide an encryption key and a symmetric algorithm for token encryption
 * 
 * @author Nicolas Thibault
 *
 */
public interface EncryptionParametersAccessor {
	
	byte[] getTokenEncryptionKey();
	
	String getTokenEncryptionSymmetricAlgorithm();

}
