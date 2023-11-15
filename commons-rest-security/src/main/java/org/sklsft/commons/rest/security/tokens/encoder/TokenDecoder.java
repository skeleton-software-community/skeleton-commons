package org.sklsft.commons.rest.security.tokens.encoder;

/**
 * used to decode a Token from a String
 * 
 * @author Nicolas Thibault, Abdessalam El Jai, Alexandre Rupp
 *
 */
public interface TokenDecoder<T> {

	T decode(String token);

}
