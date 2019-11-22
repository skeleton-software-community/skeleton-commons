package org.sklsft.commons.log;

import org.junit.BeforeClass;
import org.junit.Test;
import org.sklsft.commons.crypto.serialization.JsonSerializer;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AccessLoggerTest {

	private static AccessLogger logger;

	
	@BeforeClass
	public static void setUpBeforeClass() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		logger = new AccessLogger(new JsonSerializer(objectMapper));
	}
	
	
	@Test
	public void test() {
		
		logger.logRequest("TEST", "my request message", new Dummy(1L,"dummy request"));
		
		logger.logResponse("TEST", "my response message", new Dummy(2L,"dummy response"), 10L, "200", "OK");
	}
}
