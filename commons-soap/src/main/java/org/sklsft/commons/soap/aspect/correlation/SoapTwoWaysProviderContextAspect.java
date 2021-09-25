package org.sklsft.commons.soap.aspect.correlation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.sklsft.commons.log.aspects.RequestContextAspectTemplate;
import org.sklsft.commons.log.context.RequestChannels;
import org.sklsft.commons.log.context.RequestContext;
import org.springframework.core.annotation.Order;

/**
 * An aspect to put the transaction id and correlation id in the {@link RequestContext}. This will be useful for logs tracking.
 * 
 * @author Nicolas Thibault
 *
 */
@Aspect
@Order(0)
public class SoapTwoWaysProviderContextAspect extends RequestContextAspectTemplate {

	public SoapTwoWaysProviderContextAspect() {
		super(RequestChannels.HTTP_SOAP);
	}
	

	@Override
	@Pointcut("@annotation(org.sklsft.commons.soap.annotations.SoapTwoWaysProvider)")
	protected void onPointcut() {}
	

	@Override
	protected String getCorrelationId(JoinPoint joinPoint) {
		return null;
	}
}
