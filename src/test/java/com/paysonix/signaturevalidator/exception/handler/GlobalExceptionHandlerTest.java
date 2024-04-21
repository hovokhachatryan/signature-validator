package com.paysonix.signaturevalidator.exception.handler;

import com.paysonix.signaturevalidator.api.dto.ProblemDetailDto;
import com.paysonix.signaturevalidator.exception.EmptyParameterException;
import com.paysonix.signaturevalidator.exception.HashGenerationException;
import com.paysonix.signaturevalidator.exception.InvalidTokenException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler = new GlobalExceptionHandler();
    private MockHttpServletRequest request = new MockHttpServletRequest();

    @BeforeEach
    public void setUp() {
        handler = new GlobalExceptionHandler();
        request = new MockHttpServletRequest();
        request.setRequestURI("/api/operation");
    }

    @Test
    public void handleInvalidTokenException_ReturnsForbiddenResponse() {
        InvalidTokenException exception = new InvalidTokenException("InvalidTokenException message");
        ResponseEntity<ProblemDetailDto> response = handler.handleInvalidTokenException(exception, request);

        ProblemDetailDto body = response.getBody();
        assertEquals("InvalidTokenException message", body.details());
        assertEquals("/api/operation", body.resource());
        assertEquals(403, body.statusCode());

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    public void handleIllegalArgumentException_ReturnsBadRequestResponse() {
        EmptyParameterException exception = new EmptyParameterException("EmptyParameterException message");
        ResponseEntity<ProblemDetailDto> response = handler.handleEmptyParameterException(exception, request);

        ProblemDetailDto body = response.getBody();
        assertEquals("EmptyParameterException message", body.details());
        assertEquals("/api/operation", body.resource());
        assertEquals(400, body.statusCode());

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void handleNullPointerException_ReturnsInternalServerErrorResponse() {
        HashGenerationException exception = new HashGenerationException("HashGenerationException message");
        ResponseEntity<ProblemDetailDto> response = handler.handleHashGenerationException(exception, request);

        ProblemDetailDto body = response.getBody();
        assertEquals("HashGenerationException message", body.details());
        assertEquals("/api/operation", body.resource());
        assertEquals(500, body.statusCode());

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
