package org.sklsft.commons.mapper.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class StringToObjectConverter {
	
	public static Object getObjectFromString(String value, Class<?> clazz) {
		
		if (value.equals("")) {
			return null;
		}

		if (clazz.equals(Date.class)) {
			return Date.from(OffsetDateTime.parse(value).toInstant());
		}
		
		if (clazz.equals(LocalDate.class)) {
			return LocalDate.parse(value, DateTimeFormatter.ISO_DATE);
		}

		if (clazz.equals(Double.class)) {
			return Double.valueOf(value);
		}
		
		if (clazz.equals(BigDecimal.class)) {
			return new BigDecimal(value);
		}

		if (clazz.equals(Long.class)) {
			return Long.valueOf(value);
		}
		
		if (clazz.equals(Integer.class)) {
			return Integer.valueOf(value);
		}
		
		if (clazz.equals(Short.class)) {
			return Short.valueOf(value);
		}

		if (clazz.equals(Boolean.class)) {
			return Boolean.valueOf(value);
		}
		
		return value;

	}
}
