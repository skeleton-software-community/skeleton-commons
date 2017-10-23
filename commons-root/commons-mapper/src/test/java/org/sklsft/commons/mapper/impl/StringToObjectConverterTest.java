package org.sklsft.commons.mapper.impl;

import java.util.Date;

import org.junit.Test;

public class StringToObjectConverterTest {

	@Test
	public void testDate() {

		Date date = (Date)StringToObjectConverter.getObjectFromString("1977-12-19", Date.class);
		System.out.println(date);
		
		date = (Date)StringToObjectConverter.getObjectFromString("1977-12-19T00:00:00+01:00", Date.class);
		System.out.println(date);
	}
}
