package org.sklsft.commons.soap.aspect.logging;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.sklsft.commons.aop.AspectJUtils;
import org.sklsft.commons.api.exception.ApplicationException;
import org.sklsft.commons.log.AccessLogger;
import org.sklsft.commons.log.ErrorLogger;
import org.sklsft.commons.soap.annotations.SoapOneWaySubscriber;
import org.sklsft.commons.text.serialization.XmlSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

@Aspect
@Order(2)
public class SoapOneWaySubscriberLoggingAspect {

	private static final Logger logger = LoggerFactory.getLogger(AccessLogger.class);
	private XmlSerializer serializer = new XmlSerializer();

	private AccessLogger accessLogger;
	private ErrorLogger errorLogger;	
	private boolean traceMessage = true;
	
	
	public void setAccessLogger(AccessLogger accessLogger) {
		this.accessLogger = accessLogger;
	}
	public void setErrorLogger(ErrorLogger errorLogger) {
		this.errorLogger = errorLogger;
	}
	public void setTraceMessage(boolean traceMessage) {
		this.traceMessage = traceMessage;
	}
	
	
	@Pointcut("@annotation(org.sklsft.commons.soap.annotations.SoapOneWaySubscriber)")
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
		
		if (proxiedMethod.isAnnotationPresent(SoapOneWaySubscriber.class)) {
			return proxiedMethod.getAnnotation(SoapOneWaySubscriber.class).value();
		}
		return proxiedMethod.getName();
	}
	

	private Object getPayload(ProceedingJoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		
		try {
			return serializer.serialize(args[0]);
		} catch (Exception e) {
			logger.error("could not get message content : " + e.getMessage(), e);
			return null;
		}
	}
}
