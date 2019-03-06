package org.sklsft.commons.crypto.signature;

import java.nio.charset.StandardCharsets;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sklsft.commons.crypto.accessors.RsaPrivateKeyAccessorMock;
import org.sklsft.commons.crypto.accessors.RsaPublicKeyAccessorMock;

public class RsaSignatureVerifierTest {

	private static RsaSigner signer;
	private static RsaSignatureVerifier verifier;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		signer = new RsaSigner(new RsaPrivateKeyAccessorMock(),"test", RsaAlgorithms.RS256.getName());
		verifier = new RsaSignatureVerifier(new RsaPublicKeyAccessorMock());
	}
		
	@Test
	public void testCheck() {
		
		byte[] data = "test".getBytes(StandardCharsets.UTF_8);
		byte[] signature = signer.sign(data);
		boolean checked = verifier.checkSignature(RsaAlgorithms.RS256.getName(), "test", data, signature);
		
		Assert.assertTrue(checked);
	}
}
