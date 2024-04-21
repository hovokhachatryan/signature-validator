package com.paysonix.signaturevalidator.api.interceptor;

import com.paysonix.signaturevalidator.TestDataUtils;
import com.paysonix.signaturevalidator.config.SecurityProperties;
import com.paysonix.signaturevalidator.exception.InvalidTokenException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TokenAuthenticationInterceptorTest {

    private TokenAuthenticationInterceptor interceptor;
    private HttpServletRequest request;
    private HttpServletResponse response;

    @BeforeEach
    public void setUp() {
        SecurityProperties securityProperties = new SecurityProperties(TestDataUtils.TEST_TOKEN_KEY, TestDataUtils.TEST_HMAC_ALGO);
        interceptor = new TokenAuthenticationInterceptor(securityProperties);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }

    @Test
    public void tokenIsvValidTest() {
        when(request.getHeader("Token")).thenReturn("12345");
        assertTrue(interceptor.preHandle(request, response, new Object()));
    }

    @Test
    public void tokenIsInvalidTest() {
        when(request.getHeader("Token")).thenReturn("invalidToken");
        assertThrows(InvalidTokenException.class, () -> interceptor.preHandle(request, response, new Object()),
                "Invalid or missing token");
    }

    @Test
    public void tokenIsNullTest() {
        when(request.getHeader("Token")).thenReturn(null);
        assertThrows(InvalidTokenException.class, () -> interceptor.preHandle(request, response, new Object()),
                "Invalid or missing token");
    }
}
