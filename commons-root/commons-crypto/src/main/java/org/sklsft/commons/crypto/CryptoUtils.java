package org.sklsft.commons.crypto;

public class CryptoUtils {

	public static String getKeyAlgorithm(String algorithm) {
		
		int val = algorithm.indexOf("/");
		if (-1 == val) {
			return algorithm;
		}
		
		return algorithm.substring(0, algorithm.indexOf("/"));
		
	}
}
