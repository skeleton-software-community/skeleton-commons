package org.sklsft.commons.log.aspects;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.sklsft.commons.aop.AspectJUtils;
import org.sklsft.commons.log.AccessLogger;
import org.sklsft.commons.log.aspects.annotations.ClientLoggingInterceptorPointcut;
import org.sklsft.commons.log.aspects.annotations.LoggingAspectPointcut;
import org.sklsft.commons.log.context.RequestChannels;
import org.sklsft.commons.text.StringUtils;


public abstract class OneWayPublisherLoggingInterceptorTemplate {
	
	private RequestChannels interfaceChannel;
	
	private AccessLogger accessLogger;	
	
	private boolean traceSentBody = true;
	

	public void setAccessLogger(AccessLogger accessLogger) {
		this.accessLogger = accessLogger;
	}
	public void setTraceSentBody(boolean traceSentBody) {
		this.traceSentBody = traceSentBody;
	}
	
	
		
	public OneWayPublisherLoggingInterceptorTemplate(RequestChannels interfaceChannel) {
		super();
		this.interfaceChannel = interfaceChannel;
	}
	
	
	
	@Around("onPointcut()")
	public Object handleLogging(ProceedingJoinPoint joinPoint) throws Throwable {

		Method proxiedMethod = AspectJUtils.getProxiedMethodImplementation(joinPoint);

		String transactionType = getTransactionType(proxiedMethod);

		Object body = null;
		if (traceSentBody(proxiedMethod)) {
			body = getSentBody(joinPoint);			
		}		
		
		try {
			Object proceed = joinPoint.proceed();
			
			accessLogger.logInterfaceCall(transactionType, interfaceChannel, body);
			
			return proceed;
			
		} catch (Exception e) {
			throw e;
		}
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
