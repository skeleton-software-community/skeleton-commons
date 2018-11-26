package org.sklsft.commons.rest.client.interceptors;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

public class RestClientLoggerInterceptor implements ClientHttpRequestInterceptor {

	private final static Logger logger = LoggerFactory.getLogger(RestClientLoggerInterceptor.class);
	
	private String requestIdHeaderKey = "request-id";
	private boolean handleRequestId = true;
	private boolean traceRequestHeaders = false;
	private boolean traceRequestBody = true;
	private boolean traceResponseHeaders = false;
	private boolean traceResponseBody = false;
	
	
	public void setRequestIdHeaderKey(String requestIdHeaderKey) {
		this.requestIdHeaderKey = requestIdHeaderKey;
	}
	public void setHandleRequestId(boolean handleRequestId) {
		this.handleRequestId = handleRequestId;
	}
	public void setTraceRequestHeaders(boolean traceRequestHeaders) {
		this.traceRequestHeaders = traceRequestHeaders;
	}
	public void setTraceRequestBody(boolean traceRequestBody) {
		this.traceRequestBody = traceRequestBody;
	}
	public void setTraceResponseHeaders(boolean traceResponseHeaders) {
		this.traceResponseHeaders = traceResponseHeaders;
	}
	public void setTraceResponseBody(boolean traceResponseBody) {
		this.traceResponseBody = traceResponseBody;
	}

	
	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
		traceRequest(request, body);
		ClientHttpResponse response = execution.execute(request, body);
		traceResponse(response);
		return response;
	}

	private void traceRequest(HttpRequest request, byte[] body) throws IOException {
		logger.info("===========================http request sent===============================");
		logger.info(request.getMethod() + " " + request.getURI());
		if (handleRequestId) {
			logger.info("Request id : " + request.getHeaders().get(requestIdHeaderKey).get(0));
		}
		
		if (logger.isTraceEnabled()) {
			if (traceRequestHeaders) {
				logger.trace("Request headers : {}", request.getHeaders());
			}
			if (traceRequestBody) {
				if (!request.getMethod().equals(HttpMethod.GET)) {
					logger.trace("Request body : {}", new String(body, "UTF-8"));
				}
			}
		}
	}

	private void traceResponse(ClientHttpResponse response) throws IOException {		
		
		logger.info("Status code : {}", response.getStatusCode());
		
		if (logger.isTraceEnabled()) {
			if (traceResponseHeaders) {
				logger.trace("Response headers : {}", response.getHeaders());
			}
			if (traceResponseBody) {
				if (response.getBody() != null) {
					String responseBody = StreamUtils.copyToString(response.getBody(), StandardCharsets.UTF_8);
					logger.trace("Response body : {}", responseBody);
				}
			}
		}
		logger.info("===========================http request sent end===========================");
	}
}