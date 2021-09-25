package org.sklsft.commons.mvc.scopes;

import java.util.Map;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

/**
 * Implementation of the a JSF view scope that can be handled by spring.
 */
public class ViewScope implements Scope {
	
	public static final String NAME = "view";

    /**
     * {@inheritedDoc}
     */
	@Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        
		UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
		
		if (viewRoot != null) {
            Map<String, Object> viewMap = viewRoot.getViewMap();
            if (viewMap.containsKey(name)) {
                return viewMap.get(name);
            } else {
                Object object = objectFactory.getObject();
                viewMap.put(name, object);
                return object;
            }
        } else {
            return null;
        }
    }

    /**
     * {@inheritedDoc}
     */
    @Override
    public Object remove(String name) {
        return FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove(name);
    }

    /**
     * {@inheritedDoc}
     */
    @Override
    public String getConversationId() {
        return null;
    }

    /**
     * {@inheritedDoc}
     */
    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
        // Not supported
    }

    /**
     * {@inheritedDoc}
     */
    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }
}