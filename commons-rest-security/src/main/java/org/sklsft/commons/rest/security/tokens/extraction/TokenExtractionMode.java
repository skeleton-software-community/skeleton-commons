package org.sklsft.commons.rest.security.tokens.extraction;

import org.sklsft.commons.rest.security.annotations.AccessControl;

/**
 * Different Token extraction modes provided by annotation {@link AccessControl}
 * <li>HEADER : Token are passed through header (default mode)
 * <li>COOKIE : Token are given by cookies (useful for images to be displayed in html)
 * 
 * @author Nicolas Thibault
 *
 */
public enum TokenExtractionMode {
	
	HEADER,
	COOKIE;
}
