package com.paysonix.signaturevalidator.api.endpoint.impl;

import com.paysonix.signaturevalidator.api.dto.SignatureResponseDto;
import com.paysonix.signaturevalidator.api.endpoint.SignatureController;
import com.paysonix.signaturevalidator.exception.EmptyParameterException;
import com.paysonix.signaturevalidator.service.SignatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller implementation for handling API requests for generating signatures.
 */
@RestController
@RequestMapping("/api/operation")
@RequiredArgsConstructor
public class SignatureControllerImpl implements SignatureController {

    private final SignatureService signatureService;

    /**
     * Generates a digital signature based on the provided parameters and operation ID.
     *
     * @param operationId The unique identifier of the operation.
     * @param parameters MultiValueMap containing the parameters used to generate the signature
     *
     * @return ResponseEntity containing the generated signature wrapped in {@link SignatureResponseDto}.
     * @throws EmptyParameterException if the provided parameter map is empty.
     */
    @Override
    @PostMapping("/{operationId}")
    public ResponseEntity<SignatureResponseDto> generateSignature(
            @PathVariable String operationId,
            @RequestParam MultiValueMap<String, String> parameters) {
        if (parameters.isEmpty()) {
            throw new EmptyParameterException("Parameters should not be empty");
        }
        return ResponseEntity.ok(signatureService.processRequest(operationId, parameters));
    }
}
