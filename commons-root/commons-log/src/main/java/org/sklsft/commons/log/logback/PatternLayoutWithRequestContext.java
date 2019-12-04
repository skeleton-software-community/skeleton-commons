package org.sklsft.commons.log.logback;

import ch.qos.logback.classic.PatternLayout;

public class PatternLayoutWithRequestContext extends PatternLayout {

	static {
		PatternLayout.defaultConverterMap.put("requestId", RequestIdConverter.class.getName());
		PatternLayout.defaultConverterMap.put("channel", ChannelConverter.class.getName());
	}
}
