package com.sklsft.commons.rest.security.crypto;

import java.io.FileReader;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;

import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.sklsft.commons.crypto.accessors.RsaPrivateKeyAccessor;

public class RsaPrivateKeyAccessorMock implements RsaPrivateKeyAccessor {
	
	private static final String KEY_PATH = "src/test/resources/keys/private";
	
	@Override
	public PrivateKey getPrivateKey(String keyId) {
		try {
			byte[] key = readKey(KEY_PATH);
			EncodedKeySpec spec = new PKCS8EncodedKeySpec(key);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			return keyFactory.generatePrivate(spec);
		} catch (Exception e) {
			throw new Error(e.getMessage(), e);
		}
	}

	private byte[] readKey(String keyPath) throws IOException {
		PemReader reader = new PemReader(new FileReader(keyPath));
        PemObject pemObject = reader.readPemObject();
        byte[] content = pemObject.getContent();
        reader.close();
        return content;
	}
}
