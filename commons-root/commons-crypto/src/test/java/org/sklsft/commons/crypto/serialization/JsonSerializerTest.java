package org.sklsft.commons.crypto.serialization;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sklsft.commons.crypto.miscellaneous.TestObject;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonSerializerTest {

	private static Serializer serializer;

	@BeforeClass
	public static void setUpBeforeClass() {
		serializer = new JsonSerializer(new ObjectMapper());
	}

	@Test
	public void testJsonSerializer() throws IOException, ParseException {
		
		Date currentDate = new Date();

		TestObject object = new TestObject("test", currentDate);
		System.out.println(object.toString());
		
		String serializing = serializer.serialize(object);
		System.out.println(serializing);
		
		TestObject deserializedObject = serializer.deserialize(serializing, TestObject.class);
		System.out.println(deserializedObject.toString());
		
		Assert.assertEquals(deserializedObject,object);

	}
}
