package org.sklsft.commons.mvc.converters;

import org.sklsft.commons.api.model.SelectItem;
import org.sklsft.commons.crypto.serialization.JsonSerializer;

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
