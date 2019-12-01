package org.sklsft.commons.rest.client.interceptors;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.sklsft.commons.log.AccessLogger;
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

	private AccessLogger accessLogger;
	
	private boolean traceRequestBody = true;
	private boolean traceResponseBody = false;

	public void setAccessLogger(AccessLogger accessLogger) {
		this.accessLogger = accessLogger;
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
		String interfaceName = request.getMethod() + " " + request.getURI();
		Object sentPayload = null;
		
		if (traceRequestBody) {
			if (!request.getMethod().equals(HttpMethod.GET)) {
				sentPayload = new String(body, "UTF-8");
			}
		}
		accessLogger.logInterfaceCall(interfaceName, "REST", sentPayload);
	}

	private void traceResponse(HttpRequest request, ClientHttpResponse response, long elapsedTime) throws IOException {

		String status = response.getStatusCode().toString();
		String message = response.getStatusText();
		String receivedPayload = null;
		String interfaceName = request.getMethod() + " " + request.getURI();

		if (traceResponseBody) {
			if (response.getBody() != null) {
				receivedPayload = StreamUtils.copyToString(response.getBody(), StandardCharsets.UTF_8);
			}
		}

		accessLogger.logInterfaceCallback(interfaceName, "REST", receivedPayload, elapsedTime, status, message);
	}
}