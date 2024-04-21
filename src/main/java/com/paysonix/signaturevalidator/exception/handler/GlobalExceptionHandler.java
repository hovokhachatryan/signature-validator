package com.paysonix.signaturevalidator.exception.handler;

import com.paysonix.signaturevalidator.api.dto.ProblemDetailDto;
import com.paysonix.signaturevalidator.exception.EmptyParameterException;
import com.paysonix.signaturevalidator.exception.HashGenerationException;
import com.paysonix.signaturevalidator.exception.InvalidTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Centralized exception handling.
 *
 * This class handles specific exceptions thrown by the application, providing appropriate HTTP responses.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ProblemDetailDto> handleInvalidTokenException(InvalidTokenException e, HttpServletRequest httpRequest) {
        ProblemDetailDto problemDetailDto = new ProblemDetailDto(e.getMessage(), HttpStatus.FORBIDDEN.value(), httpRequest.getRequestURI());
        return new ResponseEntity<>(problemDetailDto, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(EmptyParameterException.class)
    public ResponseEntity<ProblemDetailDto> handleEmptyParameterException(EmptyParameterException e, HttpServletRequest httpRequest) {
        ProblemDetailDto problemDetailDto = new ProblemDetailDto(e.getMessage(), HttpStatus.BAD_REQUEST.value(), httpRequest.getRequestURI());
        return new ResponseEntity<>(problemDetailDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HashGenerationException.class)
    public ResponseEntity<ProblemDetailDto> handleHashGenerationException(HashGenerationException e, HttpServletRequest httpRequest) {
        ProblemDetailDto problemDetailDto = new ProblemDetailDto(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), httpRequest.getRequestURI());
        return new ResponseEntity<>(problemDetailDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
