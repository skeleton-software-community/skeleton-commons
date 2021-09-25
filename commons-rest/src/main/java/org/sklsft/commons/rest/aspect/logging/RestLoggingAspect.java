package org.sklsft.commons.rest.aspect.logging;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.sklsft.commons.aop.AspectJUtils;
import org.sklsft.commons.log.AccessLogger;
import org.sklsft.commons.log.ErrorLogger;
import org.sklsft.commons.log.aspects.LoggingAspectTemplate;
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
public class RestLoggingAspect extends LoggingAspectTemplate {	
	
	@Override
	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping) || "
			+ "@annotation(org.springframework.web.bind.annotation.GetMapping) || "
			+ "@annotation(org.springframework.web.bind.annotation.PostMapping) || "
			+ "@annotation(org.springframework.web.bind.annotation.PutMapping) || "
			+ "@annotation(org.springframework.web.bind.annotation.PatchMapping) || "
			+ "@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
	protected void onPointcut() {}
	
	
	@Override
	protected Object getRequestBody(ProceedingJoinPoint joinPoint) {
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
	
	
	@Override
	protected String getFallbackTransactionType(Method proxiedMethod) {
		
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = servletRequestAttributes.getRequest();
		String result = request.getMethod();		
		result = result + " " + request.getRequestURI();
		return result;
	}
}
