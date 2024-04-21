package com.paysonix.signaturevalidator.config;

import com.paysonix.signaturevalidator.api.interceptor.TokenAuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WevConfig implements WebMvcConfigurer {

    @Autowired
    private TokenAuthenticationInterceptor tokenAuthenticationInterceptor;

    /**
     * Configures the {@link InterceptorRegistry} to add the {@link TokenAuthenticationInterceptor}
     * to the application's interceptor chain.
     *
     * This interceptor will apply only to requests that match the "/api/operation/**" path pattern.
     *
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenAuthenticationInterceptor)
                .addPathPatterns("/api/operation/**");
    }


}
