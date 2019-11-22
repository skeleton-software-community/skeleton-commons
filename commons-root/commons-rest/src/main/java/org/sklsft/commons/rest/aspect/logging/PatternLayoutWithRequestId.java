package org.sklsft.commons.rest.aspect.logging;

import ch.qos.logback.classic.PatternLayout;

public class PatternLayoutWithRequestId extends PatternLayout {

	static {
		PatternLayout.defaultConverterMap.put("requestId", RequestIdConverter.class.getName());
	}
}
