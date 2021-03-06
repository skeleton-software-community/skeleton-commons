package org.sklsft.commons.log.context;

/**
 * Enumeration of the different kind of channels through which a transaction can be triggered.
 * Will be used as part of a {@link RequestContext}
 * @author Nicolas Thibault
 *
 */
public enum RequestChannels {
	HTTP_HTML,
	HTTP_SOAP,
	HTTP_REST,
	JMS,
	JMS_SOAP,
	FTP,
	BATCH;
}
