package org.sklsft.commons.rest.aspect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.sklsft.commons.rest.security.context.SecurityContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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

	private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

	private boolean traceRequestHeaders;
	private boolean traceRequestBody;
	private ObjectMapper objectMapper;

	public void setTraceRequestHeaders(boolean traceRequestHeaders) {
		this.traceRequestHeaders = traceRequestHeaders;
	}

	public void setTraceRequestBody(boolean traceRequestBody) {
		this.traceRequestBody = traceRequestBody;
	}

	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public Object handleLogging(ProceedingJoinPoint joinPoint) throws Throwable {

		long start = System.currentTimeMillis();

		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		HttpServletRequest request = servletRequestAttributes.getRequest();

		String logged = "HTTP request received : Method=" + request.getMethod() + ", URI=" + request.getRequestURI()
				+ ", Context=" + getRequestContext();

		if (traceRequestHeaders) {
			logged += ", Headers=" + getRequestHeaders(request);
		}
		if (traceRequestBody) {
			String body = getRequestBody(joinPoint);
			logged += ",Body=" + body;
		}
		logger.info(logged);

		long elapsedTime;
		
		try {
			Object proceed = joinPoint.proceed();
			elapsedTime = System.currentTimeMillis() - start;
			logged = "HTTP request processed : Response=OK, Time=" + elapsedTime + " ms";
			logger.info(logged);
			return proceed;

		} catch (Exception e) {
			elapsedTime = System.currentTimeMillis() - start;
			logged = "HTTP request processed : Response=NOK, Message=" + e.getMessage() + ", Time=" + elapsedTime + " ms";
			logger.info(logged);

			throw e;
		}
	}

	private String getRequestContext() {
		Object credentials = SecurityContextHolder.getCredentialsOrNull();
		if (credentials == null) {
			return "Public";
		} else {
			try {
				return objectMapper.writeValueAsString(credentials);
			} catch (JsonProcessingException e) {
				return "Unknown";
			}
		}
	}

	private String getRequestHeaders(HttpServletRequest request) {
		Map<String, String> headers = new HashMap<>();
		for (String header : Collections.list(request.getHeaderNames())) {
			headers.put(header, request.getHeader(header));
		}
		try {
			return objectMapper.writeValueAsString(headers);
		} catch (JsonProcessingException e) {
			return "Unknown";
		}
	}

	private String getRequestBody(ProceedingJoinPoint joinPoint) {
		Method proxiedMethod = ((MethodSignature) joinPoint.getSignature()).getMethod();
		Object[] args = joinPoint.getArgs();
		Annotation[][] paramsAnnotations = proxiedMethod.getParameterAnnotations();
		for (int i = 0; i < args.length; i++) {
			for (int j = 0; j < paramsAnnotations[i].length; j++) {
				if (paramsAnnotations[i][j].annotationType().equals(RequestBody.class)) {
					try {
						return objectMapper.writeValueAsString(args[i]);
					} catch (JsonProcessingException e) {
						return "Unknown";
					}
				}
			}
		}
		return null;
	}
}
