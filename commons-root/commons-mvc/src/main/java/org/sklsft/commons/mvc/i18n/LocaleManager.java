package org.sklsft.commons.mvc.i18n;

import java.util.Locale;


/**
 * This interface will be used to access or switch the locale used in a session
 * 
 * @author Nicolas Thibault
 *
 */
public interface LocaleManager {
	
	Locale getLocale();
	
	void switchLocale(String localeCode);

}
