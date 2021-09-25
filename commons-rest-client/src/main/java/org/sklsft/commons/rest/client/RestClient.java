package org.sklsft.commons.rest.client;

import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

/**
 * this class is responsible for calling rest services.<br>
 * It works as an adapter to the spring {@link RestTemplate}<br>
 * The rest server url should be given by a jndi variable through xml
 * configuration.
 * 
 * @author Nicolas Thibault
 * 
 */
public class RestClient {

	/*
	 * dependencies to be injected with spring xml configuration
	 */
	private String restServerUrl;
	private RestTemplate restTemplate;

	/*
	 * setters for spring xml injection
	 */
	public void setRestServerUrl(String restServerUrl) {
		this.restServerUrl = restServerUrl;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	/**
	 * call a rest url with a GET method and maps the response to T
	 */
	public <T> T getForObject(String relativeUrl, Class<T> responseClass) {

		return restTemplate
				.getForObject(getFullUrl(relativeUrl), responseClass);
	}

	/**
	 * call a rest url with a GET method with url variables and maps the
	 * response to T
	 */
	public <T> T getForObject(String relativeUrl, Class<T> responseClass,
			Map<String, ?> uriVariables) {

		return restTemplate.getForObject(getFullUrl(relativeUrl),
				responseClass, uriVariables);
	}

	/**
	 * call a rest url with a GET method and maps the response to the given
	 * parameterized type.
	 */
	public <T> T getForObject(String relativeUrl,
			ParameterizedTypeReference<T> type) {

		return restTemplate.exchange(getFullUrl(relativeUrl), HttpMethod.GET,
				null, type).getBody();

	}

	/**
	 * call a rest url with a GET method with url variables and maps the
	 * response to the given parameterized type.
	 */
	public <T> T getForObject(String relativeUrl,
			ParameterizedTypeReference<T> type, Map<String, ?> uriVariables) {

		return restTemplate.exchange(getFullUrl(relativeUrl), HttpMethod.GET,
				null, type, uriVariables).getBody();
	}

	/**
	 * call a rest url with a POST method and maps the response to T
	 */
	public <T> T postForObject(String relativeUrl, Object form,
			Class<T> responseClass) {

		return restTemplate.postForObject(getFullUrl(relativeUrl), form,
				responseClass);
	}

	/**
	 * call a rest url with a POST method with url variables and maps the
	 * response to T
	 */
	public <T> T postForObject(String relativeUrl, Object form,
			Class<T> responseClass, Map<String, ?> uriVariables) {

		return restTemplate.postForObject(getFullUrl(relativeUrl), form,
				responseClass, uriVariables);
	}

	/**
	 * call a rest url with a POST method and maps the response to the given
	 * parameterized type.
	 */
	public <T> T postForObject(String relativeUrl, Object form,
			ParameterizedTypeReference<T> type) {

		HttpEntity<Object> httpEntity = new HttpEntity<Object>(form);

		return restTemplate.exchange(getFullUrl(relativeUrl), HttpMethod.POST,
				httpEntity, type).getBody();
	}

	/**
	 * call a rest url with a POST method with url variables and maps the
	 * response to the given parameterized type.
	 */
	public <T> T postForObject(String relativeUrl, Object form,
			ParameterizedTypeReference<T> type, Map<String, ?> uriVariables) {

		HttpEntity<Object> httpEntity = new HttpEntity<Object>(form);

		return restTemplate.exchange(getFullUrl(relativeUrl), HttpMethod.POST,
				httpEntity, type, uriVariables).getBody();
	}

	/**
	 * call a rest url with a PUT method
	 */
	public void put(String relativeUrl, Object form) {

		restTemplate.put(getFullUrl(relativeUrl), form);
	}

	/**
	 * call a rest url with a PUT method with url variables
	 */
	public void put(String relativeUrl, Object form, Map<String, ?> uriVariables) {

		restTemplate.put(getFullUrl(relativeUrl), form, uriVariables);
	}

	/**
	 * call a rest url with a DELETE method with url variables
	 */

	public void delete(String relativeUrl, Map<String, ?> uriVariables) {

		restTemplate.delete(getFullUrl(relativeUrl), uriVariables);
	}

	/**
	 * Call a rest url with the specified method and maps the response to T.
	 */
	public <T> T exchange(String relativeUrl, HttpMethod method, Object form,
			Class<T> type) {

		HttpEntity<Object> httpEntity = null;
		if (null != form) {
			httpEntity = new HttpEntity<Object>(form);
		}

		return restTemplate.exchange(getFullUrl(relativeUrl), method,
				httpEntity, type).getBody();
	}

	/**
	 * Call a rest url with the specified method with url variables and maps the
	 * response to T.
	 */
	public <T> T exchange(String relativeUrl, HttpMethod method, Object form,
			Class<T> type, Map<String, ?> uriVariables) {

		HttpEntity<Object> httpEntity = null;
		if (null != form) {
			httpEntity = new HttpEntity<Object>(form);
		}

		return restTemplate.exchange(getFullUrl(relativeUrl), method,
				httpEntity, type, uriVariables).getBody();
	}

	/**
	 * Call a rest url with the specified method and maps the response to the
	 * given parameterized type.
	 */
	public <T> T exchange(String relativeUrl, HttpMethod method, Object form,
			ParameterizedTypeReference<T> type) {

		HttpEntity<Object> httpEntity = null;
		if (null != form) {
			httpEntity = new HttpEntity<Object>(form);
		}

		return restTemplate.exchange(getFullUrl(relativeUrl), method,
				httpEntity, type).getBody();
	}

	/**
	 * Call a rest url with the specified method with url variables and maps the
	 * response to the given parameterized type.
	 */
	public <T> T exchange(String relativeUrl, HttpMethod method, Object form,
			ParameterizedTypeReference<T> type, Map<String, ?> uriVariables) {

		HttpEntity<Object> httpEntity = null;
		if (null != form) {
			httpEntity = new HttpEntity<Object>(form);
		}

		return restTemplate.exchange(getFullUrl(relativeUrl), method,
				httpEntity, type, uriVariables).getBody();
	}

	private String getFullUrl(String relativeUrl) {
		return this.restServerUrl + relativeUrl;
	}
}
