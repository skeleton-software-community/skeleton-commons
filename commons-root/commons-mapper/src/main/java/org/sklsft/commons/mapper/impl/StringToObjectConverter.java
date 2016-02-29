package org.sklsft.commons.mapper.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToObjectConverter {

	private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	public static Object getObjectFromString(String value, Class<?> clazz) {
		
		if (value.equals("")) {
			return null;
		}

		if (clazz.equals(Date.class)) {
			try {
				Date date = format.parse(value);
				return date;
			} catch (ParseException e) {
				throw new IllegalArgumentException("Invalid string representation of a date", e);
			}
		}

		if (clazz.equals(Double.class)) {
			return Double.valueOf(value);
		}

		if (clazz.equals(Long.class)) {
			return Long.valueOf(value);
		}

		if (clazz.equals(Boolean.class)) {
                return Boolean.valueOf(value);
		}
		
		return value;

	}
}
