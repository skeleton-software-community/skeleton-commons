package org.sklsft.commons.mvc.converters;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.apache.commons.codec.binary.Base64;
import org.sklsft.commons.crypto.serialization.JsonSerializer;


/**
 * Implementation of a JSF converter that uses Json serialization
 * @author Nicolas Thibault
 *
 * @param <T>
 */
public class JsonSerializingConverter<T> implements Converter {
	
	private JsonSerializer serializer;
	private Class<T> clazz;
	
	public JsonSerializingConverter(Class<T> clazz, JsonSerializer serializer) {
		super();
		this.clazz = clazz;
		this.serializer = serializer;
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		if (value == null || "".equals(value)) {
			return null;
		}
		
		try {
			return serializer.deserialize(new String(Base64.decodeBase64(value)), clazz);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if (value == null) {
			return "";
		}
		
		try {
			return Base64.encodeBase64URLSafeString(serializer.serialize(value).getBytes());
		} catch (IOException e) {
			return "";
		}
	}
}
