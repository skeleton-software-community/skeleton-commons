package org.sklsft.commons.crypto.serialization;

import java.io.IOException;
import java.text.ParseException;

import org.sklsft.commons.crypto.encoding.AesJsonObjectEncoder;


/**
 * A very basic interface to serialize/deserialize objects<br>
 * Used by an {@link AesJsonObjectEncoder}
 * 
 * @author Nicolas Thibault
 *
 */
public interface Serializer {

	String serialize (Object object) throws IOException;
	
	<T> T deserialize (String arg, Class<T> targetClass) throws ParseException, IOException;
}
