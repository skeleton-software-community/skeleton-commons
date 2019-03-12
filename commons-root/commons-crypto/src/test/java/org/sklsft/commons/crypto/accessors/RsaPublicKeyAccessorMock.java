package org.sklsft.commons.crypto.accessors;

import java.io.FileReader;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;

public class RsaPublicKeyAccessorMock implements RsaPublicKeyAccessor {

	private static final String KEY_PATH = "src/test/resources/keys/public";

	@Override
	public PublicKey getPublicKey(String keyId) {
		try {
			byte[] key = readKey(KEY_PATH);
		    X509EncodedKeySpec spec = new X509EncodedKeySpec(key);
		    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			return keyFactory.generatePublic(spec);
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
