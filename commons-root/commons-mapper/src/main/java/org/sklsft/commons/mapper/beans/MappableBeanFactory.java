package org.sklsft.commons.mapper.beans;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class MappableBeanFactory {

	public static <T> MappableBean<T> getMappableBean(Class<T> beanClass) {
		MappableBean<T> bean = new MappableBean<T>();
		bean.beanClass = beanClass;
		bean.accessibleFields = getAccessibleFields(beanClass);
		return bean;
	}

	private static Map<String,AccessibleField> getAccessibleFields(Class<?> beanClass) {
		Map<String,AccessibleField> result = new HashMap<String,AccessibleField>();
		
		Field[] fields = beanClass.getDeclaredFields();
		for (Field field:fields) {
			try {
				PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), beanClass);
				if (propertyDescriptor.getReadMethod() != null && propertyDescriptor.getWriteMethod() != null) {
					result.put(field.getName(),new AccessibleField(field, propertyDescriptor.getReadMethod(), propertyDescriptor.getWriteMethod()));
				}
			} catch (IntrospectionException e) {
				//Nothing to do
			}
		}
		
		return result;
	}
}
