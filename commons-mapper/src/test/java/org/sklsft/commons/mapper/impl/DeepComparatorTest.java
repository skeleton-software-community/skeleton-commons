package org.sklsft.commons.mapper.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.sklsft.commons.mapper.Compared;
import org.sklsft.commons.mapper.Dummy;
import org.sklsft.commons.mapper.Fool;

public class DeepComparatorTest {

	@Test
	public void testSimpleEquals() {
		
		Date dateField = new Date();
		String stringField = "test";
		Long longField = Long.valueOf(1);
		Boolean booleanField = Boolean.TRUE;
		
		List<String> strings = Arrays.asList("test1", "test2");
		
		DeepComparator comparator = new DeepComparator(Dummy.class);
		
		Dummy dummy1 = new Dummy(longField, dateField, stringField, booleanField, strings, null);
		
		Dummy dummy2 = new Dummy(longField, dateField, stringField, booleanField, strings, null);
		
		Assert.assertTrue(comparator.areEqual(dummy1, dummy2));
	}
	
	
	@Test
	public void testSimpleNotEqualsLong() {
		
		Date dateField = new Date();
		String stringField = "test";
		Long longField = 1L;
		Boolean booleanField = Boolean.TRUE;
		
		List<String> strings = Arrays.asList("test1", "test2");
		
		DeepComparator comparator = new DeepComparator(Dummy.class);
		
		Dummy dummy1 = new Dummy(longField, dateField, stringField, booleanField, strings, null);
		
		Dummy dummy2 = new Dummy(2L, dateField, stringField, booleanField, strings, null);
		
		Assert.assertFalse(comparator.areEqual(dummy1, dummy2));
	}
	
	
	@Test
	public void testSimpleNotEqualsDate() {
		
		Date dateField = new Date();
		String stringField = "test";
		Long longField = 1L;
		Boolean booleanField = Boolean.TRUE;
		
		List<String> strings = Arrays.asList("test1", "test2");
		
		DeepComparator comparator = new DeepComparator(Dummy.class);
		
		Dummy dummy1 = new Dummy(longField, dateField, stringField, booleanField, strings, null);
		
		Dummy dummy2 = new Dummy(longField, new Date(1), stringField, booleanField, strings, null);
		
		Assert.assertFalse(comparator.areEqual(dummy1, dummy2));
	}
	
	
	@Test
	public void testSimpleNotEqualsString() {
		
		Date dateField = new Date();
		String stringField = "test";
		Long longField = 1L;
		Boolean booleanField = Boolean.TRUE;
		
		List<String> strings = Arrays.asList("test1", "test2");
		
		DeepComparator comparator = new DeepComparator(Dummy.class);
		
		Dummy dummy1 = new Dummy(longField, dateField, stringField, booleanField, strings, null);
		
		Dummy dummy2 = new Dummy(longField, dateField, "test1", booleanField, strings, null);
		
		Assert.assertFalse(comparator.areEqual(dummy1, dummy2));
	}
	
	
	@Test
	public void testSimpleNotEqualsBoolean() {
		
		Date dateField = new Date();
		String stringField = "test";
		Long longField = 1L;
		Boolean booleanField = Boolean.TRUE;
		
		List<String> strings = Arrays.asList("test1", "test2");
		
		DeepComparator comparator = new DeepComparator(Dummy.class);
		
		Dummy dummy1 = new Dummy(longField, dateField, stringField, booleanField, strings, null);
		
		Dummy dummy2 = new Dummy(longField, dateField, stringField, Boolean.FALSE, strings, null);
		
		Assert.assertFalse(comparator.areEqual(dummy1, dummy2));
	}
	
	
	@Test
	public void testSimpleNotEqualsList() {
		
		Date dateField = new Date();
		String stringField = "test";
		Long longField = 1L;
		Boolean booleanField = Boolean.TRUE;
		
		List<String> strings = Arrays.asList("test1", "test2");
		
		DeepComparator comparator = new DeepComparator(Dummy.class);
		
		Dummy dummy1 = new Dummy(longField, dateField, stringField, booleanField, strings, null);
		
		Dummy dummy2 = new Dummy(longField, dateField, stringField, booleanField, Arrays.asList("test3", "test4"), null);
		
		Assert.assertFalse(comparator.areEqual(dummy1, dummy2));
	}
	
	
	@Test
	public void testSimpleNotEqualsList2() {
		
		Date dateField = new Date();
		String stringField = "test";
		Long longField = 1L;
		Boolean booleanField = Boolean.TRUE;
		
		List<String> strings = Arrays.asList("test1", "test2");
		
		DeepComparator comparator = new DeepComparator(Dummy.class);
		
		Dummy dummy1 = new Dummy(longField, dateField, stringField, booleanField, strings, null);		
		Dummy dummy2 = new Dummy(longField, dateField, stringField, booleanField, Arrays.asList("test1", "test4"), null);
		
		Assert.assertFalse(comparator.areEqual(dummy1, dummy2));		
		
	}
	
	
	@Test
	public void testDeepCompareEquals() {
		Date dateField = new Date();
		String stringField = "test";
		Long longField = 1L;
		Boolean booleanField = Boolean.TRUE;
		
		List<String> strings = Arrays.asList("test1", "test2");
		Map<String, String> stringMap = new HashMap<>();
		stringMap.put("key1", "value1");
		stringMap.put("key2", "value2");
		
		Dummy dummy1 = new Dummy(longField, dateField, stringField, booleanField, strings, null);		
		Dummy dummy2 = new Dummy(longField, dateField, stringField, booleanField, strings, null);
		
		Compared compared1 = new Compared(longField, dateField, stringField, booleanField, strings, stringMap, dummy1, null, null);		
		Compared compared2 = new Compared(longField, dateField, stringField, booleanField, strings, stringMap, dummy2, null, null);
		
		DeepComparator comparator = new DeepComparator(Compared.class);		
		Assert.assertTrue(comparator.areEqual(compared1, compared2));
	}
	
	
	@Test
	public void testCompareNotEqualsSimpleMap1() {
		Date dateField = new Date();
		String stringField = "test";
		Long longField = 1L;
		Boolean booleanField = Boolean.TRUE;
		
		List<String> strings = Arrays.asList("test1", "test2");
		Map<String, String> stringMap1 = new HashMap<>();
		stringMap1.put("key1", "value1");
		stringMap1.put("key2", "value2");
		
		Map<String, String> stringMap2 = new HashMap<>();
		stringMap2.put("key1", "value1");
		stringMap2.put("key2", "value1");
		
		Dummy dummy1 = new Dummy(longField, dateField, stringField, booleanField, strings, null);		
		Dummy dummy2 = new Dummy(longField, dateField, stringField, booleanField, strings, null);
		
		Compared compared1 = new Compared(longField, dateField, stringField, booleanField, strings, stringMap1, dummy1, null, null);		
		Compared compared2 = new Compared(longField, dateField, stringField, booleanField, strings, stringMap2, dummy2, null, null);
		
		DeepComparator comparator = new DeepComparator(Compared.class);		
		Assert.assertFalse(comparator.areEqual(compared1, compared2));
	}
	
	
	@Test
	public void testCompareNotEqualsSimpleMap2() {
		Date dateField = new Date();
		String stringField = "test";
		Long longField = 1L;
		Boolean booleanField = Boolean.TRUE;
		
		List<String> strings = Arrays.asList("test1", "test2");
		Map<String, String> stringMap1 = new HashMap<>();
		stringMap1.put("key1", "value1");
		stringMap1.put("key2", "value2");
		
		Map<String, String> stringMap2 = new HashMap<>();
		stringMap2.put("key1", "value1");
		stringMap2.put("key3", "value3");
		
		Dummy dummy1 = new Dummy(longField, dateField, stringField, booleanField, strings, null);		
		Dummy dummy2 = new Dummy(longField, dateField, stringField, booleanField, strings, null);
		
		Compared compared1 = new Compared(longField, dateField, stringField, booleanField, strings, stringMap1, dummy1, null, null);		
		Compared compared2 = new Compared(longField, dateField, stringField, booleanField, strings, stringMap2, dummy2, null, null);
		
		DeepComparator comparator = new DeepComparator(Compared.class);		
		Assert.assertFalse(comparator.areEqual(compared1, compared2));
	}
	
	
	@Test
	public void testDeepCompareNotEquals() {
		Date dateField = new Date();
		String stringField = "test";
		Long longField = 1L;
		Boolean booleanField = Boolean.TRUE;
		
		List<String> strings = Arrays.asList("test1", "test2");
		Map<String, String> stringMap = new HashMap<>();
		stringMap.put("key1", "value1");
		stringMap.put("key2", "value2");
		
		Dummy dummy1 = new Dummy(longField, dateField, stringField, booleanField, strings, null);
		Dummy dummy2 = new Dummy(longField, dateField, "test2", booleanField, strings, null);
		
		Compared compared1 = new Compared(longField, dateField, stringField, booleanField, strings, stringMap, dummy1, null, null);		
		Compared compared2 = new Compared(longField, dateField, stringField, booleanField, strings, stringMap, dummy2, null, null);
		
		DeepComparator comparator = new DeepComparator(Compared.class);		
		Assert.assertFalse(comparator.areEqual(compared1, compared2));
	}
	
	
	@Test
	public void testDeepCompareListEquals() {
		Date dateField = new Date();
		String stringField = "test";
		Long longField = 1L;
		Boolean booleanField = Boolean.TRUE;
		
		List<String> strings = Arrays.asList("test1", "test2");
		Map<String, String> stringMap = new HashMap<>();
		stringMap.put("key1", "value1");
		stringMap.put("key2", "value2");
		
		Fool fool1 = new Fool(longField, dateField, stringField, booleanField, strings, null);		
		Fool fool2 = new Fool(longField, dateField, stringField, booleanField, strings, null);
		
		Compared compared1 = new Compared(longField, dateField, stringField, booleanField, strings, stringMap, null, Arrays.asList(fool1, fool2), null);		
		Compared compared2 = new Compared(longField, dateField, stringField, booleanField, strings, stringMap, null, Arrays.asList(fool1, fool1), null);
		
		DeepComparator comparator = new DeepComparator(Compared.class);
		
		Assert.assertTrue(comparator.areEqual(compared1, compared2));
	}
	
	
	@Test
	public void testDeepCompareListNotEquals1() {
		Date dateField = new Date();
		String stringField = "test";
		Long longField = 1L;
		Boolean booleanField = Boolean.TRUE;
		
		List<String> strings = Arrays.asList("test1", "test2");
		
		Fool fool1 = new Fool(longField, dateField, stringField, booleanField, strings, null);		
		Fool fool2 = new Fool(longField, dateField, "test2", booleanField, strings, null);
		
		Compared compared1 = new Compared(longField, dateField, stringField, booleanField, strings, null, null, Arrays.asList(fool1, fool2), null);
		Compared compared2 = new Compared(longField, dateField, stringField, booleanField, strings, null, null, Arrays.asList(fool2, fool2), null);
		
		DeepComparator comparator = new DeepComparator(Compared.class);		
		Assert.assertFalse(comparator.areEqual(compared1, compared2));
	}
	
	
	@Test
	public void testDeepCompareListNotEquals2() {
		Date dateField = new Date();
		String stringField = "test";
		Long longField = 1L;
		Boolean booleanField = Boolean.TRUE;
		
		List<String> strings = Arrays.asList("test1", "test2");
		
		Fool fool1 = new Fool(longField, dateField, stringField, booleanField, strings, null);		
		Fool fool2 = new Fool(longField, dateField, "test2", booleanField, strings, null);
		
		Compared compared1 = new Compared(longField, dateField, stringField, booleanField, strings, null, null, Arrays.asList(fool1, fool2), null);
		Compared compared2 = new Compared(longField, dateField, stringField, booleanField, strings, null, null, Arrays.asList(fool1, fool1), null);
		
		DeepComparator comparator = new DeepComparator(Compared.class);
		
		Assert.assertFalse(comparator.areEqual(compared1, compared2));
	}
	
	
	@Test
	public void testDeepCompareMapEquals() {
		Date dateField = new Date();
		String stringField = "test";
		Long longField = 1L;
		Boolean booleanField = Boolean.TRUE;
		
		List<String> strings = Arrays.asList("test1", "test2");
		Map<String, String> stringMap = new HashMap<>();
		stringMap.put("key1", "value1");
		stringMap.put("key2", "value2");
		
		Fool fool1 = new Fool(longField, dateField, stringField, booleanField, strings, null);		
		Fool fool2 = new Fool(longField, dateField, stringField, booleanField, strings, null);
		
		Map<String, Fool> foolMap1 = new HashMap<>();
		Map<String, Fool> foolMap2 = new HashMap<>();
		
		foolMap1.put("key1", fool1);
		foolMap1.put("key2", fool2);
		
		foolMap2.put("key1", fool1);
		foolMap2.put("key2", fool1);
		
		Compared compared1 = new Compared(longField, dateField, stringField, booleanField, strings, stringMap, null, null, foolMap1);		
		Compared compared2 = new Compared(longField, dateField, stringField, booleanField, strings, stringMap, null, null, foolMap2);
		
		DeepComparator comparator = new DeepComparator(Compared.class);
		
		Assert.assertTrue(comparator.areEqual(compared1, compared2));
	}
	
	
	@Test
	public void testDeepCompareMapNotEquals1() {
		Date dateField = new Date();
		String stringField = "test";
		Long longField = 1L;
		Boolean booleanField = Boolean.TRUE;
		
		List<String> strings = Arrays.asList("test1", "test2");
		Map<String, String> stringMap = new HashMap<>();
		stringMap.put("key1", "value1");
		stringMap.put("key2", "value2");
		
		Fool fool1 = new Fool(longField, dateField, stringField, booleanField, strings, null);		
		Fool fool2 = new Fool(longField, dateField, "test2", booleanField, strings, null);
		
		Map<String, Fool> foolMap1 = new HashMap<>();
		Map<String, Fool> foolMap2 = new HashMap<>();
		
		foolMap1.put("key1", fool1);
		foolMap1.put("key2", fool2);
		
		foolMap2.put("key1", fool1);
		foolMap2.put("key2", fool1);
		
		Compared compared1 = new Compared(longField, dateField, stringField, booleanField, strings, stringMap, null, null, foolMap1);		
		Compared compared2 = new Compared(longField, dateField, stringField, booleanField, strings, stringMap, null, null, foolMap2);
		
		DeepComparator comparator = new DeepComparator(Compared.class);
		
		Assert.assertFalse(comparator.areEqual(compared1, compared2));
	}
	
	
	@Test
	public void testDeepCompareMapNotEquals2() {
		Date dateField = new Date();
		String stringField = "test";
		Long longField = 1L;
		Boolean booleanField = Boolean.TRUE;
		
		List<String> strings = Arrays.asList("test1", "test2");
		Map<String, String> stringMap = new HashMap<>();
		stringMap.put("key1", "value1");
		stringMap.put("key2", "value2");
		
		Fool fool1 = new Fool(longField, dateField, stringField, booleanField, strings, null);		
		Fool fool2 = new Fool(longField, dateField, "test2", booleanField, strings, null);
		
		Map<String, Fool> foolMap1 = new HashMap<>();
		Map<String, Fool> foolMap2 = new HashMap<>();
		
		foolMap1.put("key1", fool1);
		foolMap1.put("key2", fool1);
		
		foolMap2.put("key1", fool1);
		foolMap2.put("key2", fool2);
		
		Compared compared1 = new Compared(longField, dateField, stringField, booleanField, strings, stringMap, null, null, foolMap1);		
		Compared compared2 = new Compared(longField, dateField, stringField, booleanField, strings, stringMap, null, null, foolMap2);
		
		DeepComparator comparator = new DeepComparator(Compared.class);
		
		Assert.assertFalse(comparator.areEqual(compared1, compared2));
	}
	
	
	@Test
	public void testDeepCompareMapNotEquals3() {
		Date dateField = new Date();
		String stringField = "test";
		Long longField = 1L;
		Boolean booleanField = Boolean.TRUE;
		
		List<String> strings = Arrays.asList("test1", "test2");
		Map<String, String> stringMap = new HashMap<>();
		stringMap.put("key1", "value1");
		stringMap.put("key2", "value2");
		
		Fool fool1 = new Fool(longField, dateField, stringField, booleanField, strings, null);		
		Fool fool2 = new Fool(longField, dateField, "test2", booleanField, strings, null);
		
		Map<String, Fool> foolMap1 = new HashMap<>();
		Map<String, Fool> foolMap2 = new HashMap<>();
		
		foolMap1.put("key1", fool1);
		foolMap1.put("key2", fool2);
		
		foolMap2.put("key1", fool1);
		foolMap2.put("key3", fool2);
		
		Compared compared1 = new Compared(longField, dateField, stringField, booleanField, strings, stringMap, null, null, foolMap1);		
		Compared compared2 = new Compared(longField, dateField, stringField, booleanField, strings, stringMap, null, null, foolMap2);
		
		DeepComparator comparator = new DeepComparator(Compared.class);
		
		Assert.assertFalse(comparator.areEqual(compared1, compared2));
	}
	
	
	@Test
	public void testDeepCompareIgnoreField() {
		Date dateField = new Date();
		String stringField = "test";
		Long longField = 1L;
		Boolean booleanField = Boolean.TRUE;
		
		List<String> strings = Arrays.asList("test1", "test2");
		
		Dummy dummy1 = new Dummy(longField, dateField, stringField, booleanField, strings, null);		
		Dummy dummy2 = new Dummy(longField, dateField, stringField, booleanField, strings, null);
		
		Compared compared1 = new Compared(longField, dateField, stringField, booleanField, strings, null, dummy1, null, null);		
		Compared compared2 = new Compared(longField, dateField, stringField, booleanField, strings, null, dummy2, null, null);
		
		DeepComparator comparator = new DeepComparator(Compared.class);
		
		compared1.setIgnoredField("test1");
		compared2.setIgnoredField("test2");
		
		Assert.assertTrue(comparator.areEqual(compared1, compared2));
	}
}
