package org.sklsft.commons.rest.aspect.logging;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.sklsft.commons.api.exception.ApplicationException;
import org.sklsft.commons.log.AccessLogger;
import org.sklsft.commons.log.ErrorLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
public class LoggingAspect {

	private AccessLogger accessLogger;
	private ErrorLogger errorLogger;
	
	private boolean traceRequestBody;
	private boolean traceResponseBody;
	
	
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

	

	

	@Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public Object handleLogging(ProceedingJoinPoint joinPoint) throws Throwable {

		long start = System.currentTimeMillis();

		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		HttpServletRequest request = servletRequestAttributes.getRequest();

		String transactionType = getTransactionType(request, joinPoint);

		Object body = null;
		if (traceRequestBody) {
			body = getRequestBody(joinPoint);			
		}
		
		accessLogger.logRequest(transactionType, "HTTP request received", body);

		long elapsedTime;
		
		try {
			Object proceed = joinPoint.proceed();
			elapsedTime = System.currentTimeMillis() - start;
			
			Object responseBody = null;
			if (traceResponseBody) {
				responseBody = proceed;
			}
			
			accessLogger.logResponse(transactionType, "HTTP response sent", responseBody, elapsedTime, "200", "OK");
			
			return proceed;

		} catch (ApplicationException e) {
			elapsedTime = System.currentTimeMillis() - start;
			accessLogger.logResponse(transactionType, "HTTP response sent", null, elapsedTime, e.getHttpErrorCode(), e.getMessage());
			errorLogger.logApplicationException(e);
			throw e;
		} catch (Exception e) {
			elapsedTime = System.currentTimeMillis() - start;
			accessLogger.logResponse(transactionType, "HTTP response sent", null, elapsedTime, "500", e.getMessage());
			errorLogger.logException(e);
			throw e;
		}
	}

	private String getTransactionType(HttpServletRequest request, ProceedingJoinPoint joinPoint) {
		String result = request.getMethod();
		
		result = result + " " + request.getRequestURI();
		
		return result;
		
	}
	

	private Object getRequestBody(ProceedingJoinPoint joinPoint) {
		Method proxiedMethod = ((MethodSignature) joinPoint.getSignature()).getMethod();
		Object[] args = joinPoint.getArgs();
		Annotation[][] paramsAnnotations = proxiedMethod.getParameterAnnotations();
		for (int i = 0; i < args.length; i++) {
			for (int j = 0; j < paramsAnnotations[i].length; j++) {
				if (paramsAnnotations[i][j].annotationType().equals(RequestBody.class)) {					
					return args[i];					
				}
			}
		}
		return null;
	}
}
