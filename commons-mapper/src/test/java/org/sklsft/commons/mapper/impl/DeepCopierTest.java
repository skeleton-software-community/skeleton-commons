package org.sklsft.commons.mapper.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.sklsft.commons.mapper.CannotInstantiate;
import org.sklsft.commons.mapper.Compared;
import org.sklsft.commons.mapper.Dummy;
import org.sklsft.commons.mapper.Fool;
import org.sklsft.commons.mapper.interfaces.Copier;

public class DeepCopierTest {
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	

	@Test
	public void testDeepCopy() {
		Date dateField = new Date();
		String stringField = "test";
		Long longField = 1L;
		Boolean booleanField = Boolean.TRUE;
		
		List<String> strings = new ArrayList<>(Arrays.asList("test1", "test2"));
		Map<String, String> stringMap = new HashMap<>();
		stringMap.put("key1", "value1");
		stringMap.put("key2", "value2");
		
		Dummy dummy = new Dummy(longField, dateField, stringField, booleanField, strings, null);
		
		Fool fool1 = new Fool(longField, dateField, stringField, booleanField, strings, null);		
		Fool fool2 = new Fool(longField, dateField, "test2", booleanField, strings, null);
		
		List<Fool> fools = new ArrayList<>();
		fools.add(fool1);
		fools.add(fool2);
		
		Map<String, Fool> foolMap = new HashMap<>();
		
		foolMap.put("key1", fool1);
		foolMap.put("key2", fool2);
		
		Compared src = new Compared(longField, dateField, stringField, booleanField, strings, stringMap, dummy, fools, foolMap);		
		
		Copier<Compared> copier = new DeepCopier<>(Compared.class);
		Compared dest = copier.copy(src);
		
		DeepComparator comparator = new DeepComparator(Compared.class);		
		Assert.assertTrue(comparator.areEqual(src, dest));
	}
	
	
	@Test
	public void testCannotInstantiate() {
		CannotInstantiate src = new CannotInstantiate("");
		
		Copier<CannotInstantiate> copier = new DeepCopier<>(CannotInstantiate.class);
		
		exception.expect(IllegalArgumentException.class);
		
		copier.copy(src);
		
	}
	
	
	@Test
	public void testBadClass() {
		CannotInstantiate src = new CannotInstantiate("");
		
		Copier<Compared> copier = new DeepCopier<>(Compared.class);
		
		exception.expect(IllegalArgumentException.class);
		
		copier.copy(src);
		
	}
}
