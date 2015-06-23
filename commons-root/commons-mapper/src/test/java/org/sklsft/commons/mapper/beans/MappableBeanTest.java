package org.sklsft.commons.mapper.beans;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.sklsft.commons.mapper.Dummy;

public class MappableBeanTest {
	
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
		
		Assert.assertEquals(stringField, dummyBean.getValue("stringField", dummy));
		Assert.assertEquals(dateField, dummyBean.getValue("dateField", dummy));
		Assert.assertEquals(longField, dummyBean.getValue("longField", dummy));
		Assert.assertEquals(booleanField, dummyBean.getValue("booleanField", dummy));		
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMappableBeanInvalidGetter() {
		
		Dummy dummy = new Dummy();
		dummyBean.getValue("notAccessibleField", dummy);		
		
	}
	
	@Test
	public void testMappableBeanSetter() {
		
		Date dateField = new Date();
		String stringField = "test";
		Long longField = Long.valueOf(1);
		Boolean booleanField = Boolean.TRUE;
		
		Dummy dummy = new Dummy();
		dummyBean.setValue("stringField", stringField, dummy);
		dummyBean.setValue("dateField", dateField, dummy);
		dummyBean.setValue("longField", longField, dummy);
		dummyBean.setValue("booleanField", booleanField, dummy);
		
		Assert.assertEquals(stringField, dummy.getStringField());
		Assert.assertEquals(dateField, dummy.getDateField());
		Assert.assertEquals(longField, dummy.getLongField());
		Assert.assertEquals(booleanField, dummy.isBooleanField());
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMappableBeanInvalidSetter() {
		
		Dummy dummy = new Dummy();
		dummyBean.setValue("notAccessibleField", "test", dummy);		
		
	}
}