package org.sklsft.commons.mvc.servlet.filters;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.sklsft.commons.api.context.RequestContext;
import org.sklsft.commons.api.context.RequestContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestContextFilter implements Filter {
	
	private final static Logger logger = LoggerFactory.getLogger(RequestContextFilter.class);
 
    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        logger.trace("Requset Context Filter initiated");
    }
 
    @Override
    public void destroy() {
    	logger.trace("Requset Context Filter destroyed");
    }
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
 
    	if (RequestContextHolder.getContextOrNull() == null) {
			String requestId = UUID.randomUUID().toString();
			RequestContext context = new RequestContext(requestId, "HTTP HTML");
			RequestContextHolder.bind(context);
		}
        chain.doFilter(request, response);
    }
}
