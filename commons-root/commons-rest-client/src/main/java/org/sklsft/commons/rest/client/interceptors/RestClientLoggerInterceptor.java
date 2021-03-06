package org.sklsft.commons.rest.client.interceptors;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.sklsft.commons.log.AccessLogger;
import org.sklsft.commons.log.context.RequestChannels;
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
	
	private boolean traceSentBody = true;
	private boolean traceReceivedBody = false;

	public void setAccessLogger(AccessLogger accessLogger) {
		this.accessLogger = accessLogger;
	}
	public void setTraceSentBody(boolean traceSentBody) {
		this.traceSentBody = traceSentBody;
	}
	public void setTraceReceivedBody(boolean traceReceivedBody) {
		this.traceReceivedBody = traceReceivedBody;
	}

	
	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		traceRequest(request, body);
		long start = System.currentTimeMillis();
		ClientHttpResponse response = execution.execute(request, body);
		traceResponse(request, response, start);
		return response;
	}

	private void traceRequest(HttpRequest request, byte[] body) throws IOException {
		String interfaceName = request.getMethod() + " " + request.getURI();
		Object sentPayload = null;
		
		if (traceSentBody) {
			if (!request.getMethod().equals(HttpMethod.GET)) {
				sentPayload = StreamUtils.copyToString(new ByteArrayInputStream(body), StandardCharsets.UTF_8);
			}
		}
		accessLogger.logInterfaceCall(interfaceName, RequestChannels.HTTP_REST, sentPayload);
	}

	private void traceResponse(HttpRequest request, ClientHttpResponse response, long start) throws IOException {

		String status = response.getStatusCode().toString();
		String message = response.getStatusText();
		String receivedPayload = null;
		String interfaceName = request.getMethod() + " " + request.getURI();

		if (traceReceivedBody) {
			if (response.getBody() != null) {
				receivedPayload = StreamUtils.copyToString(response.getBody(), StandardCharsets.UTF_8);
			}
		}
		
		long elapsedTime = System.currentTimeMillis() - start;

		accessLogger.logInterfaceAnswer(interfaceName, RequestChannels.HTTP_REST, receivedPayload, elapsedTime, status, message);
	}
}