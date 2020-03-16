package org.sklsft.commons.jms.aspect.correlation;

import java.util.UUID;

import javax.jms.Message;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.sklsft.commons.api.context.RequestChannels;
import org.sklsft.commons.api.context.RequestContext;
import org.sklsft.commons.api.context.RequestContextHolder;
import org.sklsft.commons.text.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

/**
 * An aspect to put the request id in a ThreadLocal. This will be useful for logs tracking.
 * 
 * @author Nicolas Thibault
 *
 */
@Aspect
@Order(0)
public class JmsContextAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(JmsContextAspect.class);

	@Pointcut("@annotation(org.springframework.jms.annotation.JmsListener)")
	private void onMessages(){}
	
	

	@Around("onMessages()")
	public Object handle(ProceedingJoinPoint joinPoint) throws Throwable {
		
		String transactionId = UUID.randomUUID().toString();
		
		String correlationId = getCorrelationId(joinPoint);
		if (StringUtils.isEmpty(correlationId)) {
			correlationId = transactionId;
		}
		
		RequestContextHolder.bind(new RequestContext(transactionId, correlationId, RequestChannels.JMS));

		try {
			
			return joinPoint.proceed();
	
		} finally {
			RequestContextHolder.unbind();
		}

	}

	private String getCorrelationId(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		Message message;
		
		try {
			for (Object arg:args) {
				if (arg instanceof Message) {
					message = (Message)arg;
					return message.getJMSCorrelationID();
				}
			}
			logger.warn("Could not get Correlation ID due to missing message argument");
			return null;
		} catch (Exception e) {
			logger.error("Could not get Correlation ID : " + e.getMessage(), e);
			return null;
		}
	}
}
