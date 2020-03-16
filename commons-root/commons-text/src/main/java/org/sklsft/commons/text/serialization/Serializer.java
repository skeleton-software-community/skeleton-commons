package org.sklsft.commons.text.serialization;

import java.io.IOException;
import java.text.ParseException;


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
