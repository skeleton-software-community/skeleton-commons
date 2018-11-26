package org.sklsft.commons.rest.aspect;

public class RestRequestContextHolder {
	private static ThreadLocal<RestRequestContext> allContexts = new ThreadLocal<>();

	public static void bind(RestRequestContext context) {
		allContexts.set(context);
	}	

	public static void unbind() {
		allContexts.remove();
	}

	public static RestRequestContext getContextOrNull() {
		return allContexts.get();
	}
}