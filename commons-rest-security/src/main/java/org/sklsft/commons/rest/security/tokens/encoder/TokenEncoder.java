package org.sklsft.commons.rest.security.tokens.encoder;

/**
 * used to encode/decode a Token to/from a String
 * 
 * @author Nicolas Thibault, Abdessalam El Jai, Alexandre Rupp
 *
 */
public interface TokenEncoder<T> {

	T decode(String token);

	String encode(T token);
}
