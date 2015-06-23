package org.sklsft.commons.mapper.beans;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AccessibleField {

	public Field field;
	public Method getter;
	public Method setter;
	
	public AccessibleField(Field field,Method getter, Method setter) {
		this.field = field;
		this.getter = getter;
		this.setter = setter;
	}

	public boolean isCompatibleWith(AccessibleField field2) {
		
		if (field.getType().getTypeParameters().length > 0) {
			return false;
		}
		
		if (field2.field.getType().getTypeParameters().length > 0) {
			return false;
		}
		
		if (!field2.field.getType().equals(field.getType())) {
			return false;
		}
		
		return true;
	}
}
