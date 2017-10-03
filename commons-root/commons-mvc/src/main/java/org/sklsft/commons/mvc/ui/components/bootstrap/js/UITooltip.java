package org.sklsft.commons.mvc.ui.components.bootstrap.js;

import java.io.IOException;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;


/**
 * customized component to attach a toooltip behavior to components
 * Attribute: - for : the component id/class to attach the tooltip behavior
 * 
 * @author Yang TAI
 *
 */
@FacesComponent(createTag = true, tagName = "tooltip", namespace="http://commons.sklsft.org/ui/components")
public class UITooltip extends UIComponentBase {
	
	@Override
	public String getFamily() {
		return "commons.sklsft.org.ui.components.bootstrap.js";
	}

	@Override
	public void encodeBegin(FacesContext context) throws IOException {
		String forValue = (String) getAttributes().get("for");

		String script = "$(function(){$('" + forValue + "').tooltip();})";

		ResponseWriter writer = context.getResponseWriter();

		writer.startElement("script", null);
		writer.writeAttribute("type", "text/javascript", null);
		writer.write(script);
		writer.endElement("script");
	}

}
