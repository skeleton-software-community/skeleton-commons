package org.sklsft.commons.mapper.beans;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.sklsft.commons.mapper.Dummy;

public class AccessibleFieldTest {
	
	private MappableBean<Dummy> dummyBean;

	@Before
	public void setUp() {
		dummyBean = MappableBeanFactory.getMappableBean(Dummy.class);
	}
	
	
	@Test
	public void testMappableBeanGetter() {
		
		Date dateField = new Date();
		String stringField = "test";
		Long longField = Long.valueOf(1);
		Boolean booleanField = Boolean.TRUE;
		
		Dummy dummy = new Dummy();
		
		dummy.setDateField(dateField);
		dummy.setBooleanField(booleanField);
		dummy.setLongField(longField);
		dummy.setStringField(stringField);
		
		Assert.assertEquals(stringField, dummyBean.accessibleFieldsMap.get("stringField").getValue(dummy));
		Assert.assertEquals(dateField, dummyBean.accessibleFieldsMap.get("dateField").getValue(dummy));
		Assert.assertEquals(longField, dummyBean.accessibleFieldsMap.get("longField").getValue(dummy));
		Assert.assertEquals(booleanField, dummyBean.accessibleFieldsMap.get("booleanField").getValue(dummy));	
		
	}
	
	
	
	@Test
	public void testSetter() {
		
		Date dateField = new Date();
		String stringField = "test";
		Long longField = Long.valueOf(1);
		Boolean booleanField = Boolean.TRUE;
		
		Dummy dummy = new Dummy();
		dummyBean.accessibleFieldsMap.get("stringField").setValue(stringField, dummy);
		dummyBean.accessibleFieldsMap.get("dateField").setValue(dateField, dummy);
		dummyBean.accessibleFieldsMap.get("longField").setValue(longField, dummy);
		dummyBean.accessibleFieldsMap.get("booleanField").setValue(booleanField, dummy);
		
		Assert.assertEquals(stringField, dummy.getStringField());
		Assert.assertEquals(dateField, dummy.getDateField());
		Assert.assertEquals(longField, dummy.getLongField());
		Assert.assertEquals(booleanField, dummy.isBooleanField());
		
	}
	
	
	@Test
	public void testIterableField() {
		AccessibleField iterableField = dummyBean.accessibleFieldsMap.get("strings");
		
		Assert.assertTrue(iterableField.isIterable());
	}
	
	@Test
	public void testGenericParameter() {
		AccessibleField iterableField = dummyBean.accessibleFieldsMap.get("strings");
		
		Assert.assertTrue(iterableField.genericParameters.get(0).equals(String.class));
	}
}