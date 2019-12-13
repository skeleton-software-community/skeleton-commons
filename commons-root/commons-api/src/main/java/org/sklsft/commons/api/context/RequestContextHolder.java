package org.sklsft.commons.api.context;

/**
 * A class containing a static {@link ThreadLocal} that holds a {@link RequestContext}.
 * @author Nicolas Thibault
 *
 */
public class RequestContextHolder {
	
	private static ThreadLocal<RequestContext> allContexts = new ThreadLocal<>();

	public static void bind(RequestContext context) {
		allContexts.set(context);
	}	

	public static void unbind() {
		allContexts.remove();
	}

	public static RequestContext getContextOrNull() {
		return allContexts.get();
	}
}