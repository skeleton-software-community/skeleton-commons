package org.sklsft.commons.mapper.beans;

import org.junit.Assert;
import org.junit.Test;
import org.sklsft.commons.mapper.Dummy;

public class MappableBeanFactoryTest {
	
	@Test
	public void testMappableBeanFactory() {
		MappableBean<Dummy> dummyBean = MappableBeanFactory.getMappableBean(Dummy.class);
		
		System.out.println(dummyBean.beanClass.getName());
		for (AccessibleField accessibleField:dummyBean.accessibleFieldsMap.values()) {
			System.out.println(accessibleField.field.getName());
			System.out.println(accessibleField.getter.getName());
			System.out.println(accessibleField.setter.getName());
			
			Assert.assertEquals(dummyBean.accessibleFieldsMap.values().size(),5);
		}
	}
}