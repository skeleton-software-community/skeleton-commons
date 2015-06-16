package org.sklsft.commons.crypto;

import java.io.IOException;
import java.text.ParseException;

import org.sklsft.commons.crypto.exception.CryptingException;
import org.sklsft.commons.crypto.serialization.Serializer;

public class ObjectEncoder {
	
	private Serializer serializer;
	
	public ObjectEncoder(Serializer serializer) {
		super();
		this.serializer = serializer;
	}

	public String encode(Object object, String symmetricAlgorithm, byte[] key) {
		try {
			return StringEncoder.encode(serializer.serialize(object),symmetricAlgorithm, key);
		} catch (IOException e) {
			throw new CryptingException("Failed to encode " + object.toString(), e);
		}
	}
	
	public <T> T decode(String cryptedText, Class<T> targetClass, String symmetricAlgorithm, byte[] key) {
		String serializing = StringEncoder.decode(cryptedText, symmetricAlgorithm, key);
		
		try {
			return serializer.deserialize(serializing, targetClass);
		} catch (ParseException | IOException e) {
			throw new CryptingException("Failed to decode " + cryptedText, e);
		}
	}
}
