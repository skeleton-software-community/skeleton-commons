package org.sklsft.commons.rest.security.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.sklsft.commons.rest.security.context.SecurityCredentials;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * The aspect responsible for getting http request security headers and ask for
 * a {@link SecurityCredentials}
 * 
 * @author Nicolas Thibault
 *
 */
@Aspect
@Order(2)
public class AuthenticationAspect {

	private SecurityContextProvider securityContextProvider;

	public void setSecurityContextProvider(
			SecurityContextProvider securityContextProvider) {
		this.securityContextProvider = securityContextProvider;
	}

	@Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public Object handleAuthentication(ProceedingJoinPoint joinPoint)
			throws Throwable {

		String token = extractHeader("token");
		String secretKey = extractHeader("secretKey");
		
		
		securityContextProvider.provideSecurityContext(token);

		try {			

			return joinPoint.proceed();

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
