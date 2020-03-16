package org.sklsft.commons.text.serialization;

import org.sklsft.commons.text.serialization.exceptions.SerializationException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * Implementation of a {@link Serializer} in Json that uses a jackson {@link ObjectMapper}
 * 
 * @author Nicolas Thibault
 *
 */
public class JsonSerializer implements Serializer {
	
	private ObjectMapper objectMapper;

	public JsonSerializer(ObjectMapper objectMapper) {
		super();
		this.objectMapper = objectMapper;
	}

	@Override
	public String serialize(Object object) {
		
		try {
			return objectMapper.writeValueAsString(object) ;
		} catch (JsonProcessingException e) {
			throw new SerializationException("failed to serialize object : " + e.getMessage(), e);
		}
	}

	@Override
	public <T> T deserialize(String arg, Class<T> targetClass) {
		
		try {
			return objectMapper.readValue(arg, targetClass);
		} catch (JsonProcessingException e) {
			throw new SerializationException("failed to deserialize object : " + e.getMessage(), e);
		}
	}

}
