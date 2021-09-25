package org.sklsft.commons.jms.aspect.logging;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.sklsft.commons.jms.annotations.JmsObjectSender;
import org.sklsft.commons.log.AccessLogger;
import org.sklsft.commons.log.aspects.OneWayPublisherLoggingInterceptorTemplate;
import org.sklsft.commons.log.context.RequestChannels;
import org.sklsft.commons.text.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

@Aspect
@Order(2)
public class JmsObjectSenderLoggingAspect extends OneWayPublisherLoggingInterceptorTemplate {
	
	private static final Logger logger = LoggerFactory.getLogger(AccessLogger.class);
	private Serializer serializer;

	public JmsObjectSenderLoggingAspect(Serializer serializer) {
		super(RequestChannels.JMS);
		this.serializer = serializer;
	}

	@Override
	@Pointcut("@annotation(org.sklsft.commons.jms.annotations.JmsObjectSender)")
	protected void onPointcut() {}

	@Override
	protected Object getSentBody(ProceedingJoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		
		try {
			return serializer.serialize(args[0]);
		} catch (Exception e) {
			logger.error("could not serialize sent object : " + e.getMessage(), e);
			return null;
		}
	}

	@Override
	protected String getFallbackTransactionType(Method proxiedMethod) {
		return proxiedMethod.getAnnotation(JmsObjectSender.class).destination();
	}
}
