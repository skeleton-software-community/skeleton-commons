package org.sklsft.commons.log;

import org.junit.BeforeClass;
import org.junit.Test;
import org.sklsft.commons.log.context.RequestChannels;
import org.sklsft.commons.text.serialization.JsonSerializer;

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
		
		logger.logRequest("MY_SERVICE", new Dummy(1L,"dummy request"));
		
		logger.logInterfaceCall("EXTERNAL_SERVICE", RequestChannels.HTTP_REST, new Dummy(2L,"dummy call"));
		
		logger.logInterfaceAnswer("EXTERNAL_SERVICE", RequestChannels.HTTP_REST, new Dummy(2L,"dummy callback"), 5L, "200", "OK");
		
		logger.logResponse("MY_SERVICE", new Dummy(4L,"dummy response"), 10L, "200", "OK");
	}
}
