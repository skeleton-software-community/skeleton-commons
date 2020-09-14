package org.sklsft.commons.mvc.converters;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter("localDateConverter")
public class LocalDateConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return LocalDate.parse(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (!(value instanceof LocalDate)) {
			throw new IllegalArgumentException("Not a LocalDate");
		}
		return ((LocalDate)value).format(DateTimeFormatter.ISO_LOCAL_DATE);
	}

}
