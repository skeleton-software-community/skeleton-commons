package org.sklsft.commons.rest.security.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.sklsft.commons.rest.security.annotations.AccessControl;
import org.sklsft.commons.rest.security.validation.AccessControlType;
import org.sklsft.commons.rest.security.validation.SecretKeyValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * The aspect responsible for getting http request security headers<br>
 * Depending on the {@link AccessControlType}, the aspect will check the secretKey, the token, and provide a security context when necessary
 * 
 * @author Nicolas Thibault
 *
 */
@Aspect
@Order(1)
public class AccessControlAspect {
	
	private static final Logger logger = LoggerFactory
			.getLogger(AccessControlAspect.class);

	private SecurityContextProvider<?> securityContextProvider;
	private SecretKeyValidator secretKeyValidator;
	

	public AccessControlAspect(SecurityContextProvider<?> securityContextProvider, SecretKeyValidator secretKeyValidator) {
		super();
		this.securityContextProvider = securityContextProvider;
		this.secretKeyValidator = secretKeyValidator;
	}
	
	

	@Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public Object handleAuthentication(ProceedingJoinPoint joinPoint)
			throws Throwable {

		
		try {
			String token = extractHeader("token");
			String secretKey = extractHeader("secretKey");
			
			AccessControlType accessControlType = AccessControlType.PRIVATE;
			
			Method proxiedMethod = ((MethodSignature) joinPoint.getSignature()).getMethod();
			AccessControl accessControl = proxiedMethod.getAnnotation(AccessControl.class);
			
			if (accessControl != null) {
				accessControlType = accessControl.value();
			}
			
			if (!accessControlType.equals(AccessControlType.PUBLIC)) {
				secretKeyValidator.validateSecretKey(secretKey);
			}
			
			if (accessControlType.equals(AccessControlType.PRIVATE)) {
				securityContextProvider.provideSecurityContext(token);
			}					

			return joinPoint.proceed();
			
		} catch (Throwable t) {
			logger.error(t.getMessage(), t);
			throw t;

		} finally {
			securityContextProvider.clearSecurityContext();
		}
	}
	

	private String extractHeader(String key) {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		return servletRequestAttributes.getRequest().getHeader(key);
	}
}
