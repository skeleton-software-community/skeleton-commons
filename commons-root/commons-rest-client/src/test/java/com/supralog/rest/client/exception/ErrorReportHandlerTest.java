package com.supralog.rest.client.exception;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sklsft.commons.api.exception.ApplicationException;
import org.sklsft.commons.api.exception.ErrorReport;
import org.sklsft.commons.rest.client.exception.ErrorReportHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ErrorReportHandlerTest {

	private static ObjectMapper objectMapper = new ObjectMapper();
	private static ErrorReportHandler errorReportHandler;

	
	@BeforeClass
	public static void setUpBeforeClass() {
		errorReportHandler = new ErrorReportHandler();
		errorReportHandler.setObjectMapper(objectMapper);
	}
	
	
	@Test
	public void testApplicationExceptionNoDetails() throws JsonProcessingException {
		
		String message = "test";
		
		ErrorReport errorReport = new ErrorReport();
		errorReport.setExceptionClassName(TestException.class.getName());
		errorReport.setMessage(message);
		
		try {
			errorReportHandler.convertErrorReport(errorReport);
		} catch (ApplicationException e) {
			Assert.assertEquals(e.getMessage(), message);
			Assert.assertTrue(e instanceof TestException);
			Assert.assertNull(e.getDetails());
			
			return;
		}
		Assert.fail();		
	}
	
	
	@Test
	public void testApplicationExceptionDummyDetails() throws JsonProcessingException {
		
		String message = "test";
		Dummy dummy = new Dummy(1L, "test");
		
		ErrorReport errorReport = new ErrorReport();
		errorReport.setExceptionClassName(TestException.class.getName());
		errorReport.setMessage(message);
		errorReport.setDetails(dummy);
		
		try {
			errorReportHandler.convertErrorReport(errorReport);
		} catch (ApplicationException e) {
			Assert.assertEquals(e.getMessage(), message);
			Assert.assertTrue(e instanceof TestException);
			Assert.assertTrue(e.getDetails().equals(dummy));

			return;
		}
		Assert.fail();
	}
}
