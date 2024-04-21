package com.paysonix.signaturevalidator.exception;

public class EmptyParameterException extends RuntimeException {
    public EmptyParameterException(String message) {
        super(message);
    }
}