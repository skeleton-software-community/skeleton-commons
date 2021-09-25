package org.sklsft.commons.crypto.encoding;

import org.sklsft.commons.crypto.exception.CryptingException;
import org.sklsft.commons.text.serialization.Serializer;

public class BasicObjectEncoder implements ObjectEncoder {
	
	private Serializer serializer;
	private StringEncoder encoder;

	
	public BasicObjectEncoder(Serializer serializer, StringEncoder encoder) {
		super();
		this.serializer = serializer;
		this.encoder = encoder;
	}

	@Override
	public String encode(Object object) {
		try {
			return encoder.encode(serializer.serialize(object));
		} catch (Exception e) {
			throw new CryptingException("Failed to encode " + object.toString(), e);
		}
	}
	
	@Override
	public <T> T decode(String cryptedText, Class<T> targetClass) {
		String serializing = encoder.decode(cryptedText);
		
		try {
			return serializer.deserialize(serializing, targetClass);
		} catch (Exception e) {
			throw new CryptingException("Failed to decode " + cryptedText, e);
		}
	}
}
