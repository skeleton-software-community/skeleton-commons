package org.sklsft.commons.rest.aspect.logging;

import org.sklsft.commons.rest.aspect.correlation.RestRequestContext;
import org.sklsft.commons.rest.aspect.correlation.RestRequestContextHolder;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class RequestIdConverter extends ClassicConverter {
	
	@Override
	public String convert(ILoggingEvent event) {
		
		RestRequestContext context = RestRequestContextHolder.getContextOrNull();
		
		if (context != null) {
			if (context.getRequestId() != null) {
				return context.getRequestId();
			} else {
				return "no request id";
			}
		} else {
			return "no request";
		}
	}
}
