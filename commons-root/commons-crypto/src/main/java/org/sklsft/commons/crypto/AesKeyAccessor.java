package org.sklsft.commons.crypto;

/**
 * An interface which implementation should provide an AES key and
 * 
 * @author Nicolas Thibault
 *
 */
public interface AesKeyAccessor {
	
	byte[] getAesKey();

}
