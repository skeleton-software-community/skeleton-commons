package org.sklsft.commons.rest.security.aspect;

import javax.servlet.http.Cookie;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.sklsft.commons.rest.security.annotations.DocumentAccessControl;
import org.sklsft.commons.rest.security.context.SecurityContextProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * The aspect responsible for getting the cookies and providing a security context.
 * 
 * @author Alexandre Rupp
 *
 */
@Aspect
public class DocumentAccessControlAspect {

	private static final Logger logger = LoggerFactory.getLogger(AccessControlAspect.class);

	private SecurityContextProvider securityContextProvider;
	

	public DocumentAccessControlAspect(SecurityContextProvider securityContextProvider) {
		super();
		this.securityContextProvider = securityContextProvider;
	}

	@Around("@annotation(documentAccessControl)")
	public Object handleAuthentication(ProceedingJoinPoint joinPoint, DocumentAccessControl documentAccessControl) throws Throwable {

		try {
			String token = extractCookie("img");
			securityContextProvider.provideUserSecurityContext(token);
			return joinPoint.proceed();

		} catch (Throwable t) {
			logger.error(t.getMessage(), t);
			throw t;
		} finally {
			securityContextProvider.clearSecurityContext();
		}
	}

	private String extractCookie(String key) {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		Cookie[] cookies = servletRequestAttributes.getRequest().getCookies();
		
		String imageToken = null;
		for(Cookie cookie : cookies){
			if(cookie.getName().equals(key)){
				imageToken = cookie.getValue();
				break;
			}
		}
		
		return imageToken;
	}
}
