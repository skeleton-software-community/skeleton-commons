package org.sklsft.commons.log.logback;

import org.sklsft.commons.api.context.RequestContext;
import org.sklsft.commons.api.context.RequestContextHolder;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class RequestIdConverter extends ClassicConverter {
	
	@Override
	public String convert(ILoggingEvent event) {
		
		RequestContext context = RequestContextHolder.getContextOrNull();
		
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
