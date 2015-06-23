package org.sklsft.commons.mapper.impl;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.sklsft.commons.mapper.Dummy;
import org.sklsft.commons.mapper.Fool;

public class BasicMapperImplTest {
	
	private BasicMapperImpl<Dummy, Fool> mapper;
	
	@Before
	public void setUp() {
		mapper = new BasicMapperImpl<Dummy, Fool> (Dummy.class, Fool.class);
	}
	
	@Test
	public void testMapFrom() {
		
		Date dateField = new Date();
		String stringField = "test";
		Long longField = Long.valueOf(1);
		Boolean booleanField = Boolean.TRUE;
		
		Fool fool = new Fool();
		fool.setStrings(new ArrayList<String>());
		
		fool.setDateField(dateField);
		fool.setBooleanField(booleanField);
		fool.setLongField(longField);
		fool.setStringField(stringField);
		
		Dummy dummy = mapper.mapFrom(new Dummy(), fool);
		
		Assert.assertEquals(dummy.getStringField(), fool.getStringField());
		Assert.assertEquals(dummy.getDateField(), fool.getDateField());
		Assert.assertEquals(dummy.getLongField(), fool.getLongField());
		Assert.assertEquals(dummy.isBooleanField(), fool.isBooleanField());
		
		Assert.assertNull(dummy.getStrings());
		
		
	}
	
	@Test
	public void testMapTo() {
		
		Date dateField = new Date();
		String stringField = "test";
		Long longField = Long.valueOf(1);
		Boolean booleanField = Boolean.TRUE;
		
		Fool fool = new Fool();
		fool.setStrings(new ArrayList<String>());
		
		fool.setDateField(dateField);
		fool.setBooleanField(booleanField);
		fool.setLongField(longField);
		fool.setStringField(stringField);
		
		Dummy dummy = mapper.mapFrom(new Dummy(), fool);
		
		Assert.assertEquals(dummy.getStringField(), fool.getStringField());
		Assert.assertEquals(dummy.getDateField(), fool.getDateField());
		Assert.assertEquals(dummy.getLongField(), fool.getLongField());
		Assert.assertEquals(dummy.isBooleanField(), fool.isBooleanField());
		
		Assert.assertNull(dummy.getStrings());
		
		
	}

}
