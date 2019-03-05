package org.sklsft.commons.crypto.encoding;

import org.sklsft.commons.crypto.accessors.AesKeyAccessor;
import org.sklsft.commons.crypto.serialization.JsonSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AesJsonObjectEncoder extends BasicObjectEncoder {	
		
	public AesJsonObjectEncoder(ObjectMapper objectMapper, AesKeyAccessor keyAccessor) {
		super(new JsonSerializer(objectMapper), new AesStringEncoder(keyAccessor));
	}
}
