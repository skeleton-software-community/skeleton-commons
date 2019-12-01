package org.sklsft.commons.rest.client.interceptors;

import java.io.IOException;
import java.util.UUID;

import org.sklsft.commons.api.context.RequestContext;
import org.sklsft.commons.api.context.RequestContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class RequestIdInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

    	String requestId = getRequestId();
    	
    	HttpHeaders headers = request.getHeaders();
    	headers.add("request-id", requestId);
    	return execution.execute(request, body);
    }

	private String getRequestId() {
		
		RequestContext context = RequestContextHolder.getContextOrNull();
		
		if (context != null) {
			if (context.getRequestId() != null) {
				return context.getRequestId();
			}
		}
		
		return UUID.randomUUID().toString();
	}
}
