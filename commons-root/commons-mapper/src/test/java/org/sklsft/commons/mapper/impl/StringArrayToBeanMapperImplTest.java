package org.sklsft.commons.mapper.impl;

import java.text.ParseException;
import java.time.OffsetDateTime;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.sklsft.commons.mapper.Dummy;

public class StringArrayToBeanMapperImplTest {
	
	private StringArrayToBeanMapperImpl<Dummy> mapper;
	
	@Before
	public void setUp() {
		mapper = new StringArrayToBeanMapperImpl<Dummy> (Dummy.class);
	}
	
	@Test
	public void testMapFrom() throws ParseException {
		
		String longField = "1";
		String dateField = "2016-01-01T00:00:00Z";
		String stringField = "test";
		String booleanField = "true";
		
		String[] objectArray = new String[]{longField,dateField,stringField,booleanField};
		
		Dummy dummy = mapper.mapFrom(new Dummy(), objectArray);
		
		Assert.assertEquals(dummy.getLongField(), Long.valueOf(objectArray[0]));
		Assert.assertEquals(dummy.getDateField(), Date.from(OffsetDateTime.parse(objectArray[1]).toInstant()));
		Assert.assertEquals(dummy.getStringField(), objectArray[2]);		
		Assert.assertEquals(dummy.isBooleanField(), Boolean.valueOf(objectArray[3]));
		
		Assert.assertNull(dummy.getStrings());
		
		
	}
}
