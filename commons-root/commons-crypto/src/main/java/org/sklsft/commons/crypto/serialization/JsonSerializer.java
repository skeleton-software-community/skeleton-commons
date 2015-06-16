package org.sklsft.commons.crypto.serialization;

import java.io.IOException;
import java.text.ParseException;

import org.codehaus.jackson.map.ObjectMapper;

public class JsonSerializer implements Serializer {
	
	private ObjectMapper objectMapper;

	public JsonSerializer(ObjectMapper objectMapper) {
		super();
		this.objectMapper = objectMapper;
	}

	@Override
	public String serialize(Object object) throws IOException {
		
		return objectMapper.writeValueAsString(object) ;
	}

	@Override
	public <T> T deserialize(String arg, Class<T> targetClass) throws ParseException, IOException {
		
		return objectMapper.readValue(arg, targetClass);
	}

}
