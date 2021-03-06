package org.sklsft.commons.mvc.ui.converters;

import org.sklsft.commons.api.model.SelectItem;
import org.sklsft.commons.text.serialization.JsonSerializer;

/**
 * {@link JsonSerializingConverter} for {@link SelectItem}
 * @author Alexandre RUPP
 *
 */
public class SelectItemConverter extends JsonSerializingConverter<SelectItem> {
	
	public SelectItemConverter(JsonSerializer serializer) {
		super(SelectItem.class, serializer);
	}
	
}
