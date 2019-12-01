package org.sklsft.commons.log.logback;

import ch.qos.logback.classic.PatternLayout;

public class PatternLayoutWithRequestId extends PatternLayout {

	static {
		PatternLayout.defaultConverterMap.put("requestId", RequestIdConverter.class.getName());
	}
}
