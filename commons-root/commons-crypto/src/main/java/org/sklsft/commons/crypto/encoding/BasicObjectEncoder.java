package org.sklsft.commons.crypto.encoding;

import java.io.IOException;
import java.text.ParseException;

import org.sklsft.commons.crypto.exception.CryptingException;
import org.sklsft.commons.crypto.serialization.Serializer;

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
		} catch (IOException e) {
			throw new CryptingException("Failed to encode " + object.toString(), e);
		}
	}
	
	@Override
	public <T> T decode(String cryptedText, Class<T> targetClass) {
		String serializing = encoder.decode(cryptedText);
		
		try {
			return serializer.deserialize(serializing, targetClass);
		} catch (ParseException | IOException e) {
			throw new CryptingException("Failed to decode " + cryptedText, e);
		}
	}
}
