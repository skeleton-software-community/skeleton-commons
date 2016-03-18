package org.sklsft.commons.mvc.converters;


import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sklsft.commons.api.model.SelectItem;
import org.sklsft.commons.crypto.serialization.JsonSerializer;



public class SelectItemConverterTest {
	
	private static SelectItemConverter selectItemConverter;	
	
	@BeforeClass
	public static void setUpBeforeClass() {
		selectItemConverter = new SelectItemConverter(new JsonSerializer(new ObjectMapper()));
	}
	
	@Test
	public void testSelectItemConverter(){
		SelectItem item = new SelectItem(0L, "test");
		SelectItem restoredItem = serializeAndDeserialize(item);
		Assert.assertEquals(item, restoredItem);
	}

	@Test
	public void testSelectItemConverterForNull(){
		SelectItem item = null;
		String serialized = selectItemConverter.getAsString(null, null, item);
		Assert.assertTrue(serialized.isEmpty());
		SelectItem restoredItem = (SelectItem) selectItemConverter.getAsObject(null, null, serialized);
		Assert.assertNull(restoredItem);
	}
	
	@Test
	public void testSelectItemConverterForEmpty12L(){
		SelectItem item = new SelectItem(12L,"");
		String serialized = selectItemConverter.getAsString(null, null, item);
		SelectItem restoredItem = (SelectItem) selectItemConverter.getAsObject(null, null, serialized);
		Assert.assertEquals(item, restoredItem);
	}
	
	@Test
	public void testSelectItemConverterForEmptyNullL(){
		SelectItem item = new SelectItem(null,"");
		String serialized = selectItemConverter.getAsString(null, null, item);
		SelectItem restoredItem = (SelectItem) selectItemConverter.getAsObject(null, null, serialized);
		Assert.assertEquals(item, restoredItem);
	}

	private SelectItem serializeAndDeserialize(SelectItem item) {
		String serialized = selectItemConverter.getAsString(null, null, item);
		SelectItem restoredItem = (SelectItem) selectItemConverter.getAsObject(null, null, serialized);
		return restoredItem;
	}
	
}
