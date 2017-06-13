package org.sklsft.commons.text;

import java.text.Normalizer;
import java.text.Normalizer.Form;

public class StringUtils {
	
	public static boolean isEmpty(Object str) {
		return (str == null || "".equals(str));
	}
	
	
	public static String unaccent(String arg) {
		if (arg == null) {
			return null;
		}
		return Normalizer.normalize(arg.toLowerCase(), Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	}
}
