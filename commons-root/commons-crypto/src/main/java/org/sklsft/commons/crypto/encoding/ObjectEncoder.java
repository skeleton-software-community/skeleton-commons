package org.sklsft.commons.crypto.encoding;

public interface ObjectEncoder {

	public String encode(Object object);	
	
	public <T> T decode(String cryptedText, Class<T> targetClass);
}
