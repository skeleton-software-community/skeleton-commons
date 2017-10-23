package org.sklsft.commons.mapper.impl;

import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.regex.Pattern;

public class StringToObjectConverter {
	
	public static Object getObjectFromString(String value, Class<?> clazz) {
		
		if (value.equals("")) {
			return null;
		}

		if (clazz.equals(Date.class)) {
			Date date = null;
			try {
				if (Pattern.matches("^[0-9]{4}[-]{1}[0-9]{2}[-]{1}[0-9]{2}$", value)) {
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					date = format.parse(value);
				} else {
					date = Date.from(OffsetDateTime.parse(value).toInstant());
				}
				return date;
			} catch (Exception e) {
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
