package org.sklsft.commons.rest.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RequestMapping;

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

	private static final Logger logger = LoggerFactory
			.getLogger(LoggingAspect.class);

	@Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public Object handleLogging(ProceedingJoinPoint joinPoint) throws Throwable {

		long start = System.currentTimeMillis();
		
		logger.info("Request : " + getRequestDescription(joinPoint));
		
		try {
		
			Object proceed = joinPoint.proceed();
			
			long elapsedTime = System.currentTimeMillis() - start;
			
			logger.info("Response : OK, Time: " + elapsedTime + " ms");
			
			return proceed;
			
		} catch (Exception e) {
			
			logger.error("Response : NOK, " + e.getMessage());
			
			throw e;
		}
	}
	

	private String getRequestDescription(ProceedingJoinPoint joinPoint) {
		
		String url = "";
		String method = "";
		Class<?> proxiedClass = joinPoint.getTarget().getClass();
		if (proxiedClass.isAnnotationPresent(RequestMapping.class)) {
			RequestMapping requestMapping = proxiedClass.getAnnotation(RequestMapping.class);
			if (requestMapping.value() != null && requestMapping.value().length > 0) {
				url += requestMapping.value()[0];
			}
		}
		
		Method proxiedMethod = ((MethodSignature) joinPoint.getSignature())
				.getMethod();
		
		RequestMapping requestMapping = proxiedMethod.getAnnotation(RequestMapping.class);
		if (requestMapping.value() != null && requestMapping.value().length > 0) {
			url += requestMapping.value()[0];
		}
		
		if (requestMapping.method() != null && requestMapping.method().length > 0) {
			method = requestMapping.method()[0].name();
		}
				
		return method + " " + url;
	}

}
