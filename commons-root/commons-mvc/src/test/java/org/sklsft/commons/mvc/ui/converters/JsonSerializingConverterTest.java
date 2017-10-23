package org.sklsft.commons.mvc.ui.converters;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sklsft.commons.api.model.SelectItem;
import org.sklsft.commons.crypto.serialization.JsonSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonSerializingConverterTest {
	
private static JsonSerializingConverter<SelectItem> converter;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		converter = new JsonSerializingConverter<SelectItem>(SelectItem.class, new JsonSerializer(new ObjectMapper()));
	}

	@Test
	public void testJsonSerializingConverter() {
		
		SelectItem item = new SelectItem(0L,"test");
		
		String serializeResult = converter.getAsString(null, null, item);
		
		SelectItem deserializeResult = (SelectItem) converter.getAsObject(null, null, serializeResult);
		
		Assert.assertEquals(deserializeResult.getId(), item.getId());
		Assert.assertEquals(deserializeResult.getLabel(), item.getLabel());
		
	}	
}
