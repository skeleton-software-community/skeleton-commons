package org.sklsft.commons.api.model;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class SelectItemTest {
	private static SelectItem item1;
	private static SelectItem item2;
	private static SelectItem item3;

	@BeforeClass
	public static void setUpBeforeClass() {
		item1 = new SelectItem(5L, "hello");
		item2 = new SelectItem(5L, "");
		item3 = new SelectItem(5l, null);
	}

	@Test(expected=NullPointerException.class)
	public void testSelectItemComparatorNullPointerException() throws NullPointerException {
		item1.compareTo(null);		
	}
	
	/*
	 * Test Comparator :
	 */
	
	@Test
	public void testSelectItemComparator_NullLabelVsNullLabel(){
		// Given a selectItem with an null label
		SelectItem item = new SelectItem(5L, null);
		// When it is compared to a SelectItem with a null label
		int order = item.compareTo(item3);		
		// Then it should have the same order
		Assert.assertEquals(0, order);
	}
	
	@Test
	public void testSelectItemComparator_NullLabelVsEmptyLabel(){
		// Given a selectItem with an empty label string
		SelectItem item = new SelectItem(5L, "");
		// When it is compared to a SelectItem with a null label
		int order = item.compareTo(item3);		
		// Then it should be ordered after
		Assert.assertTrue(order > 0);
	}
	
	@Test
	public void testSelectItemComparator_NullLabelVsNonEmptyLabel(){
		// Given a selectItem with a NON empty label string
		SelectItem item = new SelectItem(5L, "a");
		// When it is compared to a SelectItem with a null label
		int order = item.compareTo(item3);		
		// Then it should be ordered after
		Assert.assertTrue(order > 0);
	}
	
	@Test
	public void testSelectItemComparator_EmptyLabelVsEmptyLabel(){
		// Given a selectItem with an empty label string
		SelectItem item = new SelectItem(5L, "");
		// When it is compared to a SelectItem with an empty label
		int order = item.compareTo(item2);		
		// Then it should have the same order
		Assert.assertEquals(0, order);
	}
	
	@Test
	public void testSelectItemComparator_EmptyLabelVsNonEmptyLabel(){
		// Given a selectItem with a NON empty label string
		SelectItem item = new SelectItem(5L, "ahah");
		// When it is compared to a SelectItem with an empty label
		int order = item.compareTo(item2);		
		// Then it should be ordered after
		Assert.assertTrue(order > 0);
	}
	
	@Test
	public void testSelectItemComparator_NonEmptyLabelVsNonEmptyLabel_HigherAlphabeticOrder(){
		// Given a selectItem with a NON empty label string
		SelectItem item = new SelectItem(5L, "ahah");
		// When it is compared to a SelectItem with a non empty label whith a HIGHER rank in the alphabetical order
		int order = item.compareTo(item1);		
		// Then it should be ordered before
		Assert.assertTrue(order<0);
	}
	
	@Test
	public void testSelectItemComparator_NonEmptyLabelVsNonEmptyLabel_SameAlphabeticOrder(){
		// Given a selectItem with a NON empty label string
		SelectItem item = new SelectItem(5L, "hello");
		// When it is compared to a SelectItem with a non empty label whith the SAME rank in the alphabetical order
		int order = item.compareTo(item1);		
		// Then it should be ordered before
		Assert.assertEquals(0, order);
	}
	
	@Test
	public void testSelectItemComparator_NonEmptyLabelVsNonEmptyLabel_LowerAlphabeticOrder(){
		// Given a selectItem with a NON empty label string
		SelectItem item = new SelectItem(5L, "xx");
		// When it is compared to a SelectItem with a non empty label whith a LOWER rank in the alphabetical order
		int order = item.compareTo(item2);		
		// Then it should be ordered before
		Assert.assertTrue(order>0);
	}
	
	// NOTE: Test between "aaa" and "1" and "a" vs "A" are depending on the String.compareTo implementation of Java and are asserted to have ever been tested.

}
