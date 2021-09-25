package org.sklsft.commons.mvc.i18n;

import java.util.Locale;

import javax.faces.context.FacesContext;


/**
 * Simple implementation of {@link LocaleManager} for JSF
 * 
 * @author Nicolas Thibault
 *
 */
public class JsfLocaleManager implements LocaleManager {	
	
	private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

	@Override
	public Locale getLocale() {
		return this.locale;
	}

	@Override
	public void switchLocale(String localeCode) {
		this.locale = new Locale(localeCode);
		FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
	}
}
