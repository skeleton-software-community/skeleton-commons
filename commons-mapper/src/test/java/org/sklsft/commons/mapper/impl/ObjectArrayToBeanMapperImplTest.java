package org.sklsft.commons.mapper.impl;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.sklsft.commons.mapper.Dummy;

public class ObjectArrayToBeanMapperImplTest {
	
	private ObjectArrayToBeanMapperImpl<Dummy> mapper;
	
	@Before
	public void setUp() {
		mapper = new ObjectArrayToBeanMapperImpl<Dummy> (Dummy.class);
	}
	
	@Test
	public void testMapFrom() {
		
		Date dateField = new Date();
		String stringField = "test";
		Long longField = Long.valueOf(1);
		Boolean booleanField = Boolean.TRUE;
		
		Object[] objectArray = new Object[]{longField,dateField,stringField,booleanField};
		
		Dummy dummy = mapper.mapFrom(new Dummy(), objectArray);
		
		Assert.assertEquals(dummy.getLongField(), objectArray[0]);
		Assert.assertEquals(dummy.getDateField(), objectArray[1]);
		Assert.assertEquals(dummy.getStringField(), objectArray[2]);		
		Assert.assertEquals(dummy.isBooleanField(), objectArray[3]);
		
		Assert.assertNull(dummy.getStrings());
		
		
	}
	
	@Test
	public void testMapFromWithStartField() {
		
		Date dateField = new Date();
		String stringField = "test";
		Boolean booleanField = Boolean.TRUE;
		
		Object[] objectArray = new Object[]{dateField,stringField,booleanField};
		
		Dummy dummy = mapper.mapFrom(new Dummy(), objectArray,1);
		
		Assert.assertNull(dummy.getLongField());
		Assert.assertEquals(dummy.getDateField(), objectArray[0]);
		Assert.assertEquals(dummy.getStringField(), objectArray[1]);		
		Assert.assertEquals(dummy.isBooleanField(), objectArray[2]);
		
		Assert.assertNull(dummy.getStrings());
		
		
	}
}
