package org.sklsft.commons.mvc.aspects;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.sklsft.commons.api.exception.ApplicationException;
import org.sklsft.commons.mvc.annotations.AjaxMethod;
import org.sklsft.commons.mvc.messages.MessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This aspect executes around a method annotated with {@link org.sklsft.commons.mvc.annotations.AjaxMethod}
 * <br>It should be used in any method that binds an ajax action where no redirection should be done
 * <br>In that case : 
 * <li>we log the method call
 * <li>we execute the method
 * <li>If no exception is thrown, we log and display a successful result
 * <li>Else we log and display an error
 * The treatment of the error depends if it is an {@link ApplicationException} 
 * <br>The way we display a message is delegated to the {@link MessageHandler}
 * 
 * @author Nicolas Thibault
 *
 */
@Aspect
public class AjaxMethodAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(AjaxMethodAspect.class);
	
	private MessageHandler messageHandler;
	
	public void setMessageHandler(MessageHandler messageHandler) {
		this.messageHandler = messageHandler;
	}
	


	@Pointcut("@annotation(org.sklsft.commons.mvc.annotations.AjaxMethod)")
	private void ajaxMethods(){}
	
	
	@Around("ajaxMethods()")
	public void execute(ProceedingJoinPoint joinPoint) throws Throwable {
		Method proxiedMethod = ((MethodSignature) joinPoint.getSignature()).getMethod();
		AjaxMethod ajaxMethod = proxiedMethod.getAnnotation(AjaxMethod.class);
		String value = ajaxMethod.value();
		
		try {
			logger.info(value);
			joinPoint.proceed();
			messageHandler.displayInfo(value + ".success");
			logger.info("completed");
		} catch (ApplicationException e) {
			messageHandler.displayError(e.getMessage());
			logger.error("failed : " + e.getClass().getSimpleName() + " - " + e.getMessage());
		} catch (Exception e) {
			messageHandler.displayError(value + ".failure");
			logger.error("failed : " + e.getClass().getSimpleName() + " - " + e.getMessage());
		}
	}

}
