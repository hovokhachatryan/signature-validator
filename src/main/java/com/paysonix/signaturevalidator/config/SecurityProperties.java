package com.paysonix.signaturevalidator.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Configuration properties for security settings of the application.
 *
 * This record holds key configuration details necessary for securing the application,
 * such as authentication tokens and HMAC algorithms.
 */
@Component
public record SecurityProperties(String key, String hmacAlgorithm) {

    public SecurityProperties(@Value("${security.token.key}") String key,
                              @Value("${security.hmac.algorithm}") String hmacAlgorithm) {
        this.key = key;
        this.hmacAlgorithm = hmacAlgorithm;
    }
}
