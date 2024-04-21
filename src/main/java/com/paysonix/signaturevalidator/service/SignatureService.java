package com.paysonix.signaturevalidator.service;

import com.paysonix.signaturevalidator.api.dto.SignatureResponseDto;
import org.springframework.util.MultiValueMap;

/**
 * Interface defining the operations for signature services used within the application.
 */
public interface SignatureService {

    /**
     * Processes the request for generating a signature based on the given operation ID and parameters.
     *
     * @param operationId A unique identifier for the operation.
     * @param parameters A {@link MultiValueMap} containing the parameters used for generating the signature.
     *
     * @return A {@link SignatureResponseDto} that encapsulates the results of the signature generation process,
     *         including status and the generated signatures.
     */
    SignatureResponseDto processRequest(String operationId, MultiValueMap<String, String> parameters);

}
