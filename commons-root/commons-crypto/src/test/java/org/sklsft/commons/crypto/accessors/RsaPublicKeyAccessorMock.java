package org.sklsft.commons.crypto.accessors;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import org.apache.commons.codec.binary.Base64;

public class RsaPublicKeyAccessorMock implements RsaPublicKeyAccessor {

	private static final String KEY_PATH = "src/test/resources/keys/public";

	@Override
	public PublicKey getPublicKey(String keyId) {
		try {
			String key = new String(Files.readAllBytes(Paths.get(KEY_PATH)), StandardCharsets.UTF_8);
		    X509EncodedKeySpec spec = new X509EncodedKeySpec(Base64.decodeBase64(key));
		    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			return keyFactory.generatePublic(spec);
		} catch (Exception e) {
			throw new Error(e.getMessage(), e);
		}
	}

	
}
