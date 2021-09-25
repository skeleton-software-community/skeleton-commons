package org.sklsft.commons.crypto.accessors;

import java.security.PrivateKey;

public interface RsaPrivateKeyAccessor {

	PrivateKey getPrivateKey(String keyId);
}
