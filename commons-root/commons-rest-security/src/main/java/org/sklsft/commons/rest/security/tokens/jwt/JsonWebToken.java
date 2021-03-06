package org.sklsft.commons.rest.security.tokens.jwt;

import java.io.Serializable;

public class JsonWebToken<H,B> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/*
	 * properties
	 */
	private H header;
	private B body;
	private byte[] payload;
	private byte[] signature;
	
	
	
	/*
	 * constructors
	 */
	public JsonWebToken() {
		super();
	}
	
	public JsonWebToken(H header, B body) {
		super();
		this.header = header;
		this.body = body;
	}
	/*
	 * getters and setters
	 */
	public H getHeader() {
		return header;
	}
	public void setHeader(H header) {
		this.header = header;
	}
	public B getBody() {
		return body;
	}
	public void setBody(B body) {
		this.body = body;
	}
	public byte[] getPayload() {
		return payload;
	}
	public void setPayload(byte[] payload) {
		this.payload = payload;
	}
	public byte[] getSignature() {
		return signature;
	}
	public void setSignature(byte[] signature) {
		this.signature = signature;
	}
}
