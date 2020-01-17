package org.sklsft.commons.rest.client.interceptors;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.sklsft.commons.api.context.RequestChannels;
import org.sklsft.commons.log.AccessLogger;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

/**
 * An interceptor to be associated to a {link RestTemplate} that aims at:
 * <li>Logging when you send the request
 * <li>Logging when you receive the response
 * 
 * @author Nicolas Thibault
 *
 */
public class RestClientLoggerInterceptor implements ClientHttpRequestInterceptor {

	private AccessLogger accessLogger;
	
	private boolean traceRequestPayload = true;
	private boolean traceResponsePayload = false;

	public void setAccessLogger(AccessLogger accessLogger) {
		this.accessLogger = accessLogger;
	}
	public void setTraceRequestPayload(boolean traceRequestPayload) {
		this.traceRequestPayload = traceRequestPayload;
	}
	public void setTraceResponsePayload(boolean traceResponsePayload) {
		this.traceResponsePayload = traceResponsePayload;
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
		
		if (traceRequestPayload) {
			if (!request.getMethod().equals(HttpMethod.GET)) {
				sentPayload = new String(body, "UTF-8");
			}
		}
		accessLogger.logInterfaceCall(interfaceName, RequestChannels.HTTP_REST, sentPayload);
	}

	private void traceResponse(HttpRequest request, ClientHttpResponse response, long elapsedTime) throws IOException {

		String status = response.getStatusCode().toString();
		String message = response.getStatusText();
		String receivedPayload = null;
		String interfaceName = request.getMethod() + " " + request.getURI();

		if (traceResponsePayload) {
			if (response.getBody() != null) {
				receivedPayload = StreamUtils.copyToString(response.getBody(), StandardCharsets.UTF_8);
			}
		}

		accessLogger.logInterfaceAnswer(interfaceName, RequestChannels.HTTP_REST, receivedPayload, elapsedTime, status, message);
	}
}