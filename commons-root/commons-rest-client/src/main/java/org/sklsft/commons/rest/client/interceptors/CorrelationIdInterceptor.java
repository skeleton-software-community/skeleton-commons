package org.sklsft.commons.rest.client.interceptors;

import java.io.IOException;

import org.sklsft.commons.api.context.RequestContext;
import org.sklsft.commons.api.context.RequestContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

/**
 * An interceptor to be associated to a {link RestTemplate} that aims at:
 * Injecting a correlationId from the {@link RequestContext} in a HTTP header
 * 
 * @author Nicolas Thibault
 *
 */
public class CorrelationIdInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

    	String correlationId = getCorrelationId();
    	
    	if (correlationId != null) {
	    	HttpHeaders headers = request.getHeaders();
	    	headers.add("correlation-id", correlationId);
    	}
    	return execution.execute(request, body);
    }

	private String getCorrelationId() {
		
		RequestContext context = RequestContextHolder.getContextOrNull();
		
		if (context != null) {
			if (context.getCorrelationId() != null) {
				return context.getCorrelationId();
			}
		}
		
		return null;
	}
}
