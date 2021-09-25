package org.sklsft.commons.log.aspects;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.sklsft.commons.aop.AspectJUtils;
import org.sklsft.commons.api.exception.ApplicationException;
import org.sklsft.commons.log.AccessLogger;
import org.sklsft.commons.log.ErrorLogger;
import org.sklsft.commons.log.aspects.annotations.LoggingAspectPointcut;
import org.sklsft.commons.text.StringUtils;


public abstract class LoggingAspectTemplate {
	
	protected AccessLogger accessLogger;
	protected ErrorLogger errorLogger;
	
	private boolean traceRequestBody = true;
	private boolean traceResponseBody = true;
	
	
	public void setAccessLogger(AccessLogger accessLogger) {
		this.accessLogger = accessLogger;
	}
	public void setErrorLogger(ErrorLogger errorLogger) {
		this.errorLogger = errorLogger;
	}
	public void setTraceRequestBody(boolean traceRequestBody) {
		this.traceRequestBody = traceRequestBody;
	}
	public void setTraceResponseBody(boolean traceResponseBody) {
		this.traceResponseBody = traceResponseBody;
	}
	
	
	@Around("onPointcut()")
	public Object handleLogging(ProceedingJoinPoint joinPoint) throws Throwable {

		long start = System.currentTimeMillis();
		
		Method proxiedMethod = AspectJUtils.getProxiedMethodImplementation(joinPoint);

		String transactionType = getTransactionType(proxiedMethod);

		Object body = null;
		if (traceRequestBody(proxiedMethod)) {
			body = getRequestBody(joinPoint);			
		}
		
		accessLogger.logRequest(transactionType, body);

		long elapsedTime;
		
		try {
			Object proceed = joinPoint.proceed();
			elapsedTime = System.currentTimeMillis() - start;
			
			Object responseBody = null;
			if (traceResponseBody(proxiedMethod)) {
				responseBody = getResponseBody(proceed);
			}
			
			accessLogger.logResponse(transactionType, responseBody, elapsedTime, "200", "OK");
			
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
	
	
	protected Object getResponseBody(Object proceed) {
		return proceed;
	}
	
	
	protected abstract void onPointcut();
	

	protected abstract Object getRequestBody(ProceedingJoinPoint joinPoint);
	
	
	protected boolean traceRequestBody(Method proxiedMethod) {
		boolean result = traceRequestBody;
		
		if (result) {
			if (proxiedMethod.isAnnotationPresent(LoggingAspectPointcut.class)) {
				result = proxiedMethod.getAnnotation(LoggingAspectPointcut.class).traceRequestBody();
			}
		}
		
		return result;
	}
	
	
	protected boolean traceResponseBody(Method proxiedMethod) {
		boolean result = traceResponseBody;
		
		if (result) {
			if (proxiedMethod.isAnnotationPresent(LoggingAspectPointcut.class)) {
				result = proxiedMethod.getAnnotation(LoggingAspectPointcut.class).traceResponseBody();
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
