package org.sklsft.commons.mapper.beans;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Representation of a field with its getters and setters methods<br>
 * properties are public because of intensive use in mappers, ...
 * Generic parameters are built at instantiation
 * 
 * @author NTHI
 *
 */
public class AccessibleField {

	public Field field;
	public Method getter;
	public Method setter;
	public Class<?> fieldClass;
	public boolean isParameterized;
	public List<Class<?>> genericParameters;
	public boolean isList;
	public boolean isSet;
	public boolean isCollection;
	public boolean isMap;
	
	
	/**
	 * constructor
	 */
	public AccessibleField(Field field, Method getter, Method setter) {
		this.field = field;
		this.getter = getter;
		this.setter = setter;
		this.fieldClass = field.getType();
		this.isParameterized = isParameterized();
		this.genericParameters = getGenericParameters();		
		this.isList = isList();
		this.isSet = isSet();
		this.isCollection = isList || isSet;
		this.isMap = isMap();
	}
	
	
	public Object getValue(Object object) {
		
		try {
			return getter.invoke(object);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	
	public void setValue(Object fieldValue, Object object) {
		
		try {
			setter.invoke(object, fieldValue);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new IllegalArgumentException(e);
		}
	}

	
	/**
	 * determines wheter the field can be mapped with another field
	 * Parameterized fields are excluded for the moment
	 * @param field2
	 * @return
	 */
	public boolean isCompatibleWith(AccessibleField field2) {
		
		if (isParameterized()) {
			return false;
		}
		
		if (field2.isParameterized()) {
			return false;
		}
		
		if (!field2.field.getType().equals(field.getType())) {
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * Determines whether the field has Generic Parameters
	 * @return
	 */
	private boolean isParameterized() {
		return field.getType().getTypeParameters().length > 0;
	}
	
	
	/**
	 * construct the Parameterized fields through setters arguments
	 * If the field has no Generic Parameters, returns an empty list
	 * @return
	 */
	private List<Class<?>> getGenericParameters() {
		
		List<Class<?>> result = new ArrayList<Class<?>>(1);
		
		if (isParameterized()) {
			ParameterizedType parameterizedType = (ParameterizedType) setter.getGenericParameterTypes()[0];
			Type[] types = parameterizedType.getActualTypeArguments();
			
			for (Type type:types) {
				result.add((Class<?>)type);
			}
		}
		return result;
	}
	
	
	private boolean isList() {
		return List.class.isAssignableFrom(field.getType());
	}
	
	private boolean isSet() {
		return Set.class.isAssignableFrom(field.getType());
	}
	
	private boolean isMap() {
		return Map.class.isAssignableFrom(field.getType());
	}
}
