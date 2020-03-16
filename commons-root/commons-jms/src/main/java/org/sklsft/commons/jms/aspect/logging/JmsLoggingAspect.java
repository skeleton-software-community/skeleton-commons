package org.sklsft.commons.jms.aspect.logging;

import java.lang.reflect.Method;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.sklsft.commons.aop.AspectJUtils;
import org.sklsft.commons.api.exception.ApplicationException;
import org.sklsft.commons.jms.annotations.JmsChannel;
import org.sklsft.commons.log.AccessLogger;
import org.sklsft.commons.log.ErrorLogger;
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
public class JmsLoggingAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(AccessLogger.class);

	private AccessLogger accessLogger;
	private ErrorLogger errorLogger;
	
	private boolean traceMessage;
	
	
	public void setAccessLogger(AccessLogger accessLogger) {
		this.accessLogger = accessLogger;
	}
	public void setErrorLogger(ErrorLogger errorLogger) {
		this.errorLogger = errorLogger;
	}
	public void setTraceMessage(boolean traceMessage) {
		this.traceMessage = traceMessage;
	}
	
	
	@Pointcut("@annotation(org.springframework.jms.annotation.JmsListener)")
	private void onMessages(){}
	
	

	@Around("onMessages()")
	public Object handleLogging(ProceedingJoinPoint joinPoint) throws Throwable {

		long start = System.currentTimeMillis();

		String transactionType = getTransactionType(joinPoint);

		Object body = null;
		if (traceMessage) {
			body = getPayload(joinPoint);			
		}
		
		accessLogger.logRequest(transactionType, body);

		long elapsedTime;
		
		try {
			Object proceed = joinPoint.proceed();
			elapsedTime = System.currentTimeMillis() - start;
			
			accessLogger.logResponse(transactionType, null, elapsedTime, "200", "OK");
			
			return proceed;

		} catch (ApplicationException e) {
			elapsedTime = System.currentTimeMillis() - start;
			accessLogger.logResponse(transactionType, null, elapsedTime, e.getHttpErrorCode(), e.getMessage());
			errorLogger.logApplicationException(e);
			throw e;
		} catch (Exception e) {
			elapsedTime = System.currentTimeMillis() - start;
			accessLogger.logResponse(transactionType, null, elapsedTime, "500", e.getMessage());
			errorLogger.logException(e);
			throw e;
		}
	}

	private String getTransactionType(ProceedingJoinPoint joinPoint) {
		
		Method proxiedMethod = AspectJUtils.getProxiedMethodImplementation(joinPoint);
		
		if (proxiedMethod.isAnnotationPresent(JmsChannel.class)) {
			return proxiedMethod.getAnnotation(JmsChannel.class).value();
		}
		return proxiedMethod.getAnnotation(JmsListener.class).destination();
	}
	

	private Object getPayload(ProceedingJoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		
		try {
			for (Object arg:args) {
				if (arg instanceof TextMessage) {
					return (String)(((TextMessage)arg).getText());
				}
				if (arg instanceof BytesMessage) {
					BytesMessage message = (BytesMessage)arg;
					int lentgh = (int)message.getBodyLength();
					byte[] content = new byte[lentgh];
					message.readBytes(content);
					return new String(content);
				}
			}
			logger.warn("could not get message content due to missing message argument");
			return null;
			
		} catch (JMSException e) {
			logger.error("could not get message content : " + e.getMessage(), e);
			return null;
		}
	}
}
