package org.sklsft.commons.rest.security.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.sklsft.commons.rest.security.access.AccessControlType;
import org.sklsft.commons.rest.security.access.AccessController;
import org.sklsft.commons.rest.security.annotations.AccessControl;
import org.sklsft.commons.rest.security.context.SecurityContextHolder;
import org.sklsft.commons.rest.security.tokens.TokenExtractionMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

/**
 * The aspect responsible for controlling the access to a RESTFul API<br>
 * The {@link AccessControlType} is declared with the {@link AccessControl} Annotation<br>
 * The access control is delegated to the @{link AccessController}
 * 
 * @author Nicolas Thibault, Abdessalam El Jai, Alexandre Rupp
 *
 */
@Aspect
@Order(1)
public class AccessControlAspect {

	private static final Logger logger = LoggerFactory.getLogger(AccessControlAspect.class);

	
	private AccessController accessController;
	

	public AccessControlAspect(AccessController accessController) {
		super();
		this.accessController = accessController;
	}

	@Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public Object handleHandshake(ProceedingJoinPoint joinPoint) throws Throwable {

		try {
			
			AccessControlType accessControlType = getAccessControlType(joinPoint);
			TokenExtractionMode tokenExtractionMode = getTokenExtractionMode(joinPoint);
			
			accessController.handshake(accessControlType, tokenExtractionMode);
			
			return joinPoint.proceed();
	
		} catch (Throwable t) {
			logger.error(t.getMessage(), t);
			throw t;
	
		} finally {
			SecurityContextHolder.unbindCredentials();
		}
	}
	

	private AccessControlType getAccessControlType(ProceedingJoinPoint joinPoint) {
		AccessControlType accessControlType = AccessControlType.PRIVATE;

		Method proxiedMethod = ((MethodSignature) joinPoint.getSignature()).getMethod();
		AccessControl accessControl = proxiedMethod.getAnnotation(AccessControl.class);

		if (accessControl != null) {
			accessControlType = accessControl.value();
		}
		return accessControlType;
	}
	
	private TokenExtractionMode getTokenExtractionMode(ProceedingJoinPoint joinPoint) {
		TokenExtractionMode tokenExtractionMode = TokenExtractionMode.HEADER;

		Method proxiedMethod = ((MethodSignature) joinPoint.getSignature()).getMethod();
		AccessControl accessControl = proxiedMethod.getAnnotation(AccessControl.class);

		if (accessControl != null) {
			tokenExtractionMode = accessControl.tokenExtractionMode();
		}
		return tokenExtractionMode;
	}
}
