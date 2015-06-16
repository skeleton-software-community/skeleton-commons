package org.sklsft.commons.rest.security.validation;

import org.sklsft.commons.rest.security.annotations.AccessControl;

/**
 * Different Access control types provided by annotation {@link AccessControl}
 * <li>PUBLIC : no control should be done
 * <li>ANONYMOUS : the only secretKey will be checked
 * <li>PRIVATE : both secretKey and token will be checked
 * 
 * @author Nicolas Thibault
 *
 */
public enum AccessControlType {
	
	PUBLIC,
	ANONYMOUS,
	PRIVATE;
}
