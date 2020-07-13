package org.sklsft.commons.log.aspects;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.sklsft.commons.aop.AspectJUtils;
import org.sklsft.commons.api.exception.ApplicationException;
import org.sklsft.commons.log.AccessLogger;
import org.sklsft.commons.log.ErrorLogger;
import org.sklsft.commons.log.aspects.annotations.ClientLoggingInterceptorPointcut;
import org.sklsft.commons.log.aspects.annotations.LoggingAspectPointcut;
import org.sklsft.commons.log.context.RequestChannels;
import org.sklsft.commons.text.StringUtils;


public abstract class ClientLoggingInterceptorTemplate {
	
	private RequestChannels interfaceChannel;
	
	private AccessLogger accessLogger;	
	
	private boolean traceSentBody = true;
	private boolean traceReceivedBody = false;

	public void setAccessLogger(AccessLogger accessLogger) {
		this.accessLogger = accessLogger;
	}
	public void setTraceSentBody(boolean traceSentBody) {
		this.traceSentBody = traceSentBody;
	}
	public void setTraceReceivedBody(boolean traceReceivedBody) {
		this.traceReceivedBody = traceReceivedBody;
	}
	
		
	public ClientLoggingInterceptorTemplate(RequestChannels interfaceChannel) {
		super();
		this.interfaceChannel = interfaceChannel;
	}
	
	
	
	@Around("onPointcut()")
	public Object handleLogging(ProceedingJoinPoint joinPoint) throws Throwable {

		long start = System.currentTimeMillis();
		
		Method proxiedMethod = AspectJUtils.getProxiedMethodImplementation(joinPoint);

		String transactionType = getTransactionType(proxiedMethod);

		Object body = null;
		if (traceSentBody(proxiedMethod)) {
			body = getSentBody(joinPoint);			
		}
		
		accessLogger.logInterfaceCall(transactionType, interfaceChannel, body);

		long elapsedTime;
		Object responseBody = null;
		
		try {
			Object proceed = joinPoint.proceed();
			elapsedTime = System.currentTimeMillis() - start;			
			
			if (traceReceivedBody(proxiedMethod)) {
				responseBody = getReceivedBody(proceed);
			}
			
			accessLogger.logInterfaceAnswer(transactionType, interfaceChannel, responseBody, elapsedTime, "200", "OK");
			
			return proceed;

		} catch (ApplicationException e) {
			elapsedTime = System.currentTimeMillis() - start;
			accessLogger.logInterfaceAnswer(transactionType, interfaceChannel, responseBody, elapsedTime, e.getHttpErrorCode(), e.getMessage());
			throw e;
		} catch (Exception e) {
			elapsedTime = System.currentTimeMillis() - start;
			accessLogger.logInterfaceAnswer(transactionType, interfaceChannel, responseBody, elapsedTime, "500", e.getMessage());
			throw e;
		}
	}
	
	
	protected Object getReceivedBody(Object proceed) {
		return proceed;
	}
	
	
	protected abstract void onPointcut();
	

	protected abstract Object getSentBody(ProceedingJoinPoint joinPoint);
	
	
	protected boolean traceSentBody(Method proxiedMethod) {
		boolean result = traceSentBody;
		
		if (result) {
			if (proxiedMethod.isAnnotationPresent(ClientLoggingInterceptorPointcut.class)) {
				result = proxiedMethod.getAnnotation(ClientLoggingInterceptorPointcut.class).traceSentBody();
			}
		}
		
		return result;
	}
	
	
	protected boolean traceReceivedBody(Method proxiedMethod) {
		boolean result = traceReceivedBody;
		
		if (result) {
			if (proxiedMethod.isAnnotationPresent(ClientLoggingInterceptorPointcut.class)) {
				result = proxiedMethod.getAnnotation(ClientLoggingInterceptorPointcut.class).traceReceivedBody();
			}
		}
		
		return result;
	}
	
	
	private String getTransactionType(Method proxiedMethod) {
		
		String result = null;
		if (proxiedMethod.isAnnotationPresent(LoggingAspectPointcut.class)) {
			result = proxiedMethod.getAnnotation(LoggingAspectPointcut.class).value();
		}
		
		if (StringUtils.isEmpty(result)) {
			return getFallbackTransactionType(proxiedMethod);
		}
		return result;
	}	

	protected abstract String getFallbackTransactionType(Method proxiedMethod);
}
