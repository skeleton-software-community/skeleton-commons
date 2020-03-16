package org.sklsft.commons.mvc.ui.converters;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sklsft.commons.api.model.SelectItem;
import org.sklsft.commons.text.serialization.JsonSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonSerializingConverterTest {
	
private static JsonSerializingConverter<SelectItem> converter;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		converter = new JsonSerializingConverter<SelectItem>(SelectItem.class, new JsonSerializer(new ObjectMapper()));
	}

	@Test
	public void testJsonSerializingConverter() {
		
		SelectItem item = new SelectItem("test","test");
		
		String serializeResult = converter.getAsString(null, null, item);
		
		SelectItem deserializeResult = (SelectItem) converter.getAsObject(null, null, serializeResult);
		
		Assert.assertEquals(deserializeResult.getKey(), item.getKey());
		Assert.assertEquals(deserializeResult.getLabel(), item.getLabel());
		
	}	
}
