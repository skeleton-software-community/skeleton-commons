package org.sklsft.commons.soap.aspect.logging;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.sklsft.commons.log.AccessLogger;
import org.sklsft.commons.log.aspects.LoggingAspectTemplate;
import org.sklsft.commons.soap.annotations.SoapTwoWaysProvider;
import org.sklsft.commons.text.serialization.XmlSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

@Aspect
@Order(2)
public class SoapTwoWaysProviderLoggingAspect extends LoggingAspectTemplate {

	private static final Logger logger = LoggerFactory.getLogger(AccessLogger.class);
	private XmlSerializer serializer = new XmlSerializer();

	
	@Override
	@Pointcut("@annotation(org.sklsft.commons.soap.annotations.SoapTwoWaysProvider)")
	protected void onPointcut() {}
	
	
	@Override
	protected Object getRequestBody(ProceedingJoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		
		try {
			return serializer.serialize(args[0]);
		} catch (Exception e) {
			logger.error("could not serialize request body : " + e.getMessage(), e);
			return null;
		}
	}
	
	
	@Override
	protected Object getResponseBody(Object proceed) {
		try {
			return serializer.serialize(proceed);
		} catch (Exception e) {
			logger.error("could not serialize response body : " + e.getMessage(), e);
			return null;
		}
	}
	
	
	@Override
	protected String getFallbackTransactionType(Method proxiedMethod) {
		return proxiedMethod.getAnnotation(SoapTwoWaysProvider.class).value();
	}
}
