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
	private boolean traceRequestBody = true;
	private boolean traceResponseBody = false;

	public void setRequestIdHeaderKey(String requestIdHeaderKey) {
		this.requestIdHeaderKey = requestIdHeaderKey;
	}
	public void setTraceRequestBody(boolean traceRequestBody) {
		this.traceRequestBody = traceRequestBody;
	}
	public void setTraceResponseBody(boolean traceResponseBody) {
		this.traceResponseBody = traceResponseBody;
	}

	
	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		traceRequest(request, body);
		long start = System.currentTimeMillis();
		ClientHttpResponse response = execution.execute(request, body);
		long elapsedTime = System.currentTimeMillis() - start;
		traceResponse(request, response, elapsedTime);
		return response;
	}

	private void traceRequest(HttpRequest request, byte[] body) throws IOException {
		String logged = "HTTP request sent : Method=" + request.getMethod() + ", URI=" + request.getURI();

//		if (handleRequestId) {
//			logged += ", RequestId=" + request.getHeaders().get(requestIdHeaderKey).get(0);
//		}
		
		if (traceRequestBody) {
			if (!request.getMethod().equals(HttpMethod.GET)) {
				logged += ", Body=" + new String(body, "UTF-8");
			}
		}
		logger.info(logged);
	}

	private void traceResponse(HttpRequest request, ClientHttpResponse response, long elapsedTime) throws IOException {

		String logged = "HTTP response received : Status=" + response.getStatusCode();
		
//		if (handleRequestId) {
//			logged += ", RequestId=" + request.getHeaders().get(requestIdHeaderKey).get(0);
//		} else {
//			logged += ", Method=" + request.getMethod() + ", URI=" + request.getURI();
//		}

		if (traceResponseBody) {
			if (response.getBody() != null) {
				String responseBody = StreamUtils.copyToString(response.getBody(), StandardCharsets.UTF_8);
				logged += ", Body=" + responseBody;
			}
		}
		logged += ", Time=" + elapsedTime + " ms";

		logger.info(logged);
	}
}