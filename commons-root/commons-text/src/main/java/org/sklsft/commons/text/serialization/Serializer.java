package org.sklsft.commons.text.serialization;

/**
 * A very basic interface to serialize/deserialize objects<br>
 * 
 * @author Nicolas Thibault
 *
 */
public interface Serializer {

	String serialize (Object object);
	
	<T> T deserialize (String arg, Class<T> targetClass);
}
