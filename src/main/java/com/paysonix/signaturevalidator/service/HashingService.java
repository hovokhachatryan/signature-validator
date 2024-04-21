package com.paysonix.signaturevalidator.service;

import org.springframework.util.MultiValueMap;

/**
 * Interface defining the operations for hashing services..
 */
public interface HashingService {

    /**
     * Generates a digital signature based on the provided parameters.
     * This method uses a cryptographic hashing algorithm to create a signature that
     * can be used to verify the integrity and authenticity of the parameters.
     *
     * @param parameters A {@link MultiValueMap} containing the parameters used for generating the signature.
     *                   The keys and values in this map should represent the data to be signed.
     * @return A {@link String} representing the generated digital signature in hexadecimal format.
     */
    String generateSignature(MultiValueMap<String, String> parameters);

}
