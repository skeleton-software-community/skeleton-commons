package org.sklsft.commons.mvc.loading;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.sklsft.commons.api.exception.repository.ObjectNotFoundException;
import org.sklsft.commons.api.exception.rights.AccessDeniedException;

/**
 * Aspect used around method used when loading a page in JSF
 *
 * @author Nicolas Thibault
 *
 */
@Aspect
public class PageLoadAspect {

	/**
	 * to be injected
	 */
	private PageLoadExceptionHandler exceptionHandler;

	public void setExceptionHandler(PageLoadExceptionHandler exceptionHandler) {
		this.exceptionHandler = exceptionHandler;
	}



	@Around("@annotation(org.sklsft.commons.mvc.annotations.PageLoad)")
	public void execute(ProceedingJoinPoint joinPoint) throws Throwable {
		try {

			joinPoint.proceed();
			
		} catch (ObjectNotFoundException e) {
			exceptionHandler.redirectOnMissingResource();
		} catch (AccessDeniedException e) {
			exceptionHandler.redirectOnAccessDenied();
		} catch (Exception e) {
			exceptionHandler.redirectOnException();
		}
	}

}
