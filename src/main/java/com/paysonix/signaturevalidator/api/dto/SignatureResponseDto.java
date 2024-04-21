package com.paysonix.signaturevalidator.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Represents the response structure for signature generation requests.
 */
@Data
@AllArgsConstructor
public class SignatureResponseDto {
    private SignatureResponseStatus status;
    private List<Result> result;

    /**
     * Nested class to encapsulate individual signature results.
     */
    @Data
    @AllArgsConstructor
    public static class Result {
        private String signature;
    }
}