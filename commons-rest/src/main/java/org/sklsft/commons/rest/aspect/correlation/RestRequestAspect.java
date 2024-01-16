package org.sklsft.commons.rest.aspect.correlation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.sklsft.commons.log.aspects.RequestContextAspectTemplate;
import org.sklsft.commons.log.context.RequestChannels;
import org.sklsft.commons.log.context.RequestContext;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * An aspect to put the transaction id and correlation id in the {@link RequestContext}. This will be useful for logs tracking.
 * 
 * @author Nicolas Thibault
 *
 */
@Aspect
@Order(0)
public class RestRequestAspect extends RequestContextAspectTemplate {

	public RestRequestAspect() {
		super(RequestChannels.HTTP_REST);
	}
	

	private String getHeader(String key) {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes();
		String result = servletRequestAttributes.getRequest().getHeader(key);
		return result;
	}

	@Override
	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping) || "
			+ "@annotation(org.springframework.web.bind.annotation.GetMapping) || "
			+ "@annotation(org.springframework.web.bind.annotation.PostMapping) || "
			+ "@annotation(org.springframework.web.bind.annotation.PutMapping) || "
			+ "@annotation(org.springframework.web.bind.annotation.PatchMapping) || "
			+ "@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
	protected void onPointcut() {}
	

	@Override
	protected String getCorrelationId(JoinPoint joinPoint) {
		return getHeader("correlation-id");
	}
}
