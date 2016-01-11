package org.sklsft.commons.mvc.converters;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sklsft.commons.api.model.SelectItem;
import org.sklsft.commons.crypto.serialization.JsonSerializer;

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
