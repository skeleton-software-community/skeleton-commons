package org.sklsft.commons.rest.aspect.correlation;

import java.util.UUID;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.sklsft.commons.api.context.RequestChannels;
import org.sklsft.commons.api.context.RequestContext;
import org.sklsft.commons.api.context.RequestContextHolder;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * An aspect to put the transaction id and correlation id in the {@link RequestContext}. This will be useful for logs tracking.
 * 
 * @author Nicolas Thibault
 *
 */
@Aspect
@Order(0)
public class RestRequestAspect {

	@Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public Object handle(ProceedingJoinPoint joinPoint) throws Throwable {
		
		String transactionId = UUID.randomUUID().toString();
		
		String correlationId = getHeader("correlation-id");
		if (correlationId == null) {
			correlationId = transactionId;
		}
		
		RequestContextHolder.bind(new RequestContext(transactionId, correlationId, RequestChannels.HTTP_REST));

		try {
			
			return joinPoint.proceed();
	
		} finally {
			RequestContextHolder.unbind();
		}

	}

	private String getHeader(String key) {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) org.springframework.web.context.request.RequestContextHolder.getRequestAttributes();
		String result = servletRequestAttributes.getRequest().getHeader(key);
		return result;
	}
}
