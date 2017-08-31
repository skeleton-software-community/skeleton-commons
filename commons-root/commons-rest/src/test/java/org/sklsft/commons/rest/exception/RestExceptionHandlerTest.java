package org.sklsft.commons.rest.exception;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sklsft.commons.api.exception.ApplicationException;
import org.sklsft.commons.api.exception.ErrorReport;

public class RestExceptionHandlerTest {

	private static RestExceptionHandler restExceptionHandler;

	
	@BeforeClass
	public static void setUpBeforeClass() {
		restExceptionHandler = new RestExceptionHandler();
	}
	
	
	@Test
	public void testApplicationExceptionNoDetails() {
		
		String message = "test";
		ApplicationException e = new TestException(message);
		
		ErrorReport errorReport = restExceptionHandler.handleApplicationException(e);
		
		Assert.assertEquals(errorReport.getMessage(), message);
		Assert.assertTrue(errorReport.getExceptionClassName().equals(TestException.class.getName()));
		Assert.assertNull(errorReport.getDetails());
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
	}
}
