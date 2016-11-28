package org.sklsft.commons.rest.security.context;

public interface ApplicationCredentialsRetriever<T> {

	T retrieveApplicationCredentials(String appKey);
}
