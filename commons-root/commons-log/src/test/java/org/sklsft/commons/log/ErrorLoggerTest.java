package org.sklsft.commons.log;

import org.junit.BeforeClass;
import org.junit.Test;
import org.sklsft.commons.api.exception.TechnicalError;
import org.sklsft.commons.crypto.serialization.JsonSerializer;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ErrorLoggerTest {

	private static ErrorLogger logger;

	
	@BeforeClass
	public static void setUpBeforeClass() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		logger = new ErrorLogger(new JsonSerializer(objectMapper));
	}
	
	
	@Test
	public void test() {		
		logger.logApplicationException(new TechnicalError("error", new TechnicalError("root cause")));
	}
}
