package org.sklsft.commons.mapper.impl;

import java.time.LocalDate;
import java.util.Date;

import org.junit.Test;

public class StringToObjectConverterTest {

	@Test
	public void testDate() {

		LocalDate localDate = (LocalDate)StringToObjectConverter.getObjectFromString("1977-12-19", LocalDate.class);
		System.out.println(localDate);
		
		Date date = (Date)StringToObjectConverter.getObjectFromString("1977-12-19T02:00:00.001+01:00", Date.class);
		System.out.println(date.toInstant());
	}
}
