package org.sklsft.commons.jms.aspect.logging;

import java.lang.reflect.Method;

import javax.jms.Message;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.sklsft.commons.jms.reader.MessageReader;
import org.sklsft.commons.log.AccessLogger;
import org.sklsft.commons.log.aspects.LoggingAspectTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.jms.annotation.JmsListener;

/**
 * A simple login aspect that logs :
 * <li>The request
 * <li>The response, if it is OK
 * <li>Any exception thrown
 * 
 * @author Nicolas Thibault
 *
 */
@Aspect
@Order(2)
public class JmsListenerLoggingAspect extends LoggingAspectTemplate {
	
	private static final Logger logger = LoggerFactory.getLogger(AccessLogger.class);
	
		
	@Override
	protected Object getRequestBody(ProceedingJoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		
		try {
			for (Object arg:args) {
				if (MessageReader.isMessage(arg)) {
					return MessageReader.getMessageContent((Message)arg);
				}
			}
			logger.warn("could not get message content due to missing message argument");
			return null;
			
		} catch (Exception e) {
			logger.error("could not get message content : " + e.getMessage(), e);
			return null;
		}
	}
	
	
	@Override
	protected Object getResponseBody(Object proceed) {
		return null;
	}
	
	
	@Override
	@Pointcut("@annotation(org.springframework.jms.annotation.JmsListener)")
	protected void onPointcut() {}
	
	
	@Override
	protected String getFallbackTransactionType(Method proxiedMethod) {
		return proxiedMethod.getAnnotation(JmsListener.class).destination();
	}
}
