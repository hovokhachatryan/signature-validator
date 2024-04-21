package com.paysonix.signaturevalidator.api.dto;

/**
 * This record encapsulates error details to provide structured error information in API responses
 *
 * @param details    A detailed message explaining the error.
 * @param statusCode The HTTP status code associated with the error.
 * @param resource   The URI of the resource where the error occurred.
 */
public record ProblemDetailDto(String details, int statusCode, String resource) {}
