package com.sklsft.commons.rest.security.tokens;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.sklsft.commons.api.exception.TechnicalError;
import org.sklsft.commons.rest.security.tokens.TokenExtractor;

public class TokenExtractorMock implements TokenExtractor {

	@Override
	public String extractToken(String key) {
		
		Path path = Paths.get(key);
		try {
			return Files.readAllLines(path).get(0);
		} catch (IOException e) {
			throw new TechnicalError("Failed to get token from : " + key, e);
		}
	}

}
