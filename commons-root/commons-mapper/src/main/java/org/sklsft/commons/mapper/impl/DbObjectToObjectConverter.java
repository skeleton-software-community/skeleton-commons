package org.sklsft.commons.mapper.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DbObjectToObjectConverter {
	
	public static Object getObjectFromDbObject(Object value, Class<?> clazz) {
		
		Object result = value;
		
		//Oracle patch for numbers mapping
		if (Long.class.isAssignableFrom(clazz)) {
			if (BigDecimal.class.isAssignableFrom(value.getClass())) {
				result = ((BigDecimal)value).longValue();
			}
		}
		if (Integer.class.isAssignableFrom(clazz)) {
			if (BigDecimal.class.isAssignableFrom(value.getClass())) {
				result = ((BigDecimal)value).intValue();
			}
		}
		if (Short.class.isAssignableFrom(clazz)) {
			if (BigDecimal.class.isAssignableFrom(value.getClass())) {
				result = ((BigDecimal)value).shortValue();
			}
		}
		if (Boolean.class.isAssignableFrom(clazz)) {
			if (BigDecimal.class.isAssignableFrom(value.getClass())) {
				result = ((BigDecimal)value).shortValue()>0;
			}
		}
		//Oracle patch for dates mapping
		if (LocalDate.class.isAssignableFrom(clazz)) {
			if (Date.class.isAssignableFrom(value.getClass())) {
				result = ((Date)value).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			}
		}		
		if (Date.class.isAssignableFrom(clazz)) {
			if (Date.class.isAssignableFrom(value.getClass())) {
				result = (Date)value;
			}
		}
	
		return result;

	}
}
