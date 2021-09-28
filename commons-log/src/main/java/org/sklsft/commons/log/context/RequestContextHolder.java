package org.sklsft.commons.log.context;

import org.slf4j.MDC;

/**
 * A class containing a static {@link ThreadLocal} that holds a {@link RequestContext}.
 * @author Nicolas Thibault
 *
 */
public class RequestContextHolder {
	
	private static final String TRANSACTION_ID = "transactionId";
	private static final String CORRELATION_ID = "correlationId";
	private static final String CHANNEL = "channel";
	
	
	private static ThreadLocal<RequestContext> allContexts = new ThreadLocal<>();

	public static void bind(RequestContext context) {
		allContexts.set(context);
		MDC.put(TRANSACTION_ID, context.getTransactionId());
		MDC.put(CORRELATION_ID, context.getCorrelationId());
		MDC.put(CHANNEL, context.getChannel()!=null?context.getChannel().name():null);
	}	

	public static void unbind() {
		allContexts.remove();
		MDC.remove(TRANSACTION_ID);
		MDC.remove(CORRELATION_ID);
		MDC.remove(CHANNEL);
	}

	public static RequestContext getContextOrNull() {
		return allContexts.get();
	}
}