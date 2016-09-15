package org.sklsft.commons.rest.exception;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sklsft.commons.api.exception.ApplicationException;
import org.sklsft.commons.api.exception.ErrorReport;
import org.sklsft.commons.crypto.serialization.JsonSerializer;
import org.sklsft.commons.crypto.serialization.Serializer;
import org.sklsft.commons.rest.exception.RestExceptionHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestExceptionHandlerTest {

	private static Serializer serializer;
	private static RestExceptionHandler restExceptionHandler;

	
	@BeforeClass
	public static void setUpBeforeClass() {
		serializer = new JsonSerializer(new ObjectMapper());
		restExceptionHandler = new RestExceptionHandler();
		restExceptionHandler.setSerializer(serializer);
	}
	
	
	@Test
	public void testApplicationExceptionNoDetails() {
		
		String message = "test";
		ApplicationException e = new TestException(message);
		
		ErrorReport errorReport = restExceptionHandler.handleApplicationException(e);
		
		Assert.assertEquals(errorReport.getMessage(), message);
		Assert.assertTrue(errorReport.getExceptionClassName().equals(TestException.class.getName()));
		Assert.assertNull(errorReport.getDetails());
		Assert.assertNull(errorReport.getDetailsClassName());
	}
	
	
	@Test
	public void testApplicationExceptionDummyDetails() {
		
		Dummy dummy = new Dummy();
		dummy.setLongField(1L);
		dummy.setStringField("test");
		
		String message = "test";
		ApplicationException e = new TestException(message, dummy);
		
		ErrorReport errorReport = restExceptionHandler.handleApplicationException(e);
		
		Assert.assertEquals(errorReport.getMessage(), message);
		Assert.assertTrue(errorReport.getExceptionClassName().equals(TestException.class.getName()));
		Assert.assertNotNull(errorReport.getDetails());
		Assert.assertTrue(errorReport.getDetailsClassName().equals(Dummy.class.getName()));
	}
}
