package org.sklsft.commons.mapper.beans;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MappableBeanFactory {

	public static <T> MappableBean<T> getMappableBean(Class<T> beanClass) {
		MappableBean<T> bean = new MappableBean<T>();
		bean.beanClass = beanClass;
		bean.accessibleFields = getAccessibleFields(beanClass);
		bean.accessibleFieldsMap = getAccessibleFieldsMap(bean.accessibleFields);
		return bean;
	}

	private static List<AccessibleField> getAccessibleFields(Class<?> beanClass) {
		List<AccessibleField> result = new ArrayList<>();
		
		Field[] fields = beanClass.getDeclaredFields();
		for (Field field:fields) {
			try {
				PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), beanClass);
				if (propertyDescriptor.getReadMethod() != null && propertyDescriptor.getWriteMethod() != null) {
					result.add(new AccessibleField(field, propertyDescriptor.getReadMethod(), propertyDescriptor.getWriteMethod()));
				}
			} catch (IntrospectionException e) {
				//Nothing to do
			}
		}
		
		return result;
	}
	
	private static Map<String,AccessibleField> getAccessibleFieldsMap(List<AccessibleField> accessibleFields) {
		Map<String,AccessibleField> result = new HashMap<String,AccessibleField>();
		
		for (AccessibleField field:accessibleFields) {
			result.put(field.field.getName(),field);
		}
		
		return result;
	}
}
