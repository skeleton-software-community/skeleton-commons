package org.sklsft.commons.rest.aspect.logging;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.sklsft.commons.aop.AspectJUtils;
import org.sklsft.commons.api.exception.ApplicationException;
import org.sklsft.commons.log.AccessLogger;
import org.sklsft.commons.log.ErrorLogger;
import org.sklsft.commons.rest.annotations.RestMethod;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * A simple login aspect that logs :
 * <li>The request
 * <li>The response
 * <li>Any exception thrown
 * It uses {@link AccessLogger} and {@link ErrorLogger}
 * 
 * @author Nicolas Thibault
 *
 */
@Aspect
@Order(2)
public class RestLoggingAspect {

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

	
	@Around("@annotation(org.springframework.web.bind.annotation.RequestMapping) || "
		+ "@annotation(org.springframework.web.bind.annotation.GetMapping) || "
		+ "@annotation(org.springframework.web.bind.annotation.PostMapping) || "
		+ "@annotation(org.springframework.web.bind.annotation.PutMapping) || "
		+ "@annotation(org.springframework.web.bind.annotation.PatchMapping) || "
		+ "@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
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
		
		accessLogger.logRequest(transactionType, body);

		long elapsedTime;
		
		try {
			Object proceed = joinPoint.proceed();
			elapsedTime = System.currentTimeMillis() - start;
			
			Object responseBody = null;
			if (traceResponseBody) {
				responseBody = proceed;
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

	private String getTransactionType(HttpServletRequest request, ProceedingJoinPoint joinPoint) {
		
		Method proxiedMethod = AspectJUtils.getProxiedMethodImplementation(joinPoint);
		
		if (proxiedMethod.isAnnotationPresent(RestMethod.class)) {
			return proxiedMethod.getAnnotation(RestMethod.class).value();
		}
		
		String result = request.getMethod();		
		result = result + " " + request.getRequestURI();		
		return result;		
	}
	
	

	private Object getRequestBody(ProceedingJoinPoint joinPoint) {
		Method proxiedMethod = AspectJUtils.getProxiedMethodImplementation(joinPoint);
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
