package org.sklsft.commons.log.logback;

import org.sklsft.commons.log.context.RequestContext;
import org.sklsft.commons.log.context.RequestContextHolder;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class ChannelConverter extends ClassicConverter {
	
	@Override
	public String convert(ILoggingEvent event) {
		
		RequestContext context = RequestContextHolder.getContextOrNull();
		
		if (context != null) {
			if (context.getChannel() != null) {
				return context.getChannel().name();
			}
		}
		return null;
	}
}
