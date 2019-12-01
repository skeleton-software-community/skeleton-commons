package org.sklsft.commons.api.context;

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