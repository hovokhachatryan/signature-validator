package com.paysonix.signaturevalidator.api.interceptor;

import com.paysonix.signaturevalidator.config.SecurityProperties;
import com.paysonix.signaturevalidator.exception.InvalidTokenException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * Interceptor to authenticate HTTP requests based on a token.
 * This interceptor checks the 'Token' header in the incoming request against
 * the expected token value defined in {@link SecurityProperties}.
 *
 */
@Component
@RequiredArgsConstructor
public class TokenAuthenticationInterceptor implements HandlerInterceptor {

    private static final String TOKEN_HEADER_NAME = "Token";

    private final SecurityProperties securityProperties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader(TOKEN_HEADER_NAME);
        if (!Objects.equals(token, securityProperties.key())) {
            throw new InvalidTokenException("Invalid or missing token");
        }
        return true;
    }
}
