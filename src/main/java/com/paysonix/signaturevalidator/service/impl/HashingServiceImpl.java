package com.paysonix.signaturevalidator.service.impl;

import com.paysonix.signaturevalidator.config.SecurityProperties;
import com.paysonix.signaturevalidator.exception.HashGenerationException;
import com.paysonix.signaturevalidator.service.HashingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.HexFormat;
import java.util.List;
import java.util.TreeMap;

@Service
@RequiredArgsConstructor
public class HashingServiceImpl implements HashingService {

    private final SecurityProperties securityProperties;

    @Override
    public String generateSignature(MultiValueMap<String, String> parameters) {
        String data = getRequestParameters(parameters);

        try {
            Mac sha256Hmac = Mac.getInstance(securityProperties.hmacAlgorithm());
            SecretKeySpec secretKey = new SecretKeySpec(securityProperties.key().getBytes(), sha256Hmac.getAlgorithm());
            sha256Hmac.init(secretKey);
            byte[] bytes = sha256Hmac.doFinal(data.getBytes());
            return HexFormat.of().formatHex(bytes);
        } catch (Exception e) {
            throw new HashGenerationException(String.format("Failed to generate HMAC signature using algorithm %s: cause %s",
                    securityProperties.hmacAlgorithm(), e.getMessage()));
        }
    }

    private String getRequestParameters(MultiValueMap<String, String> parameters) {
        TreeMap<String, List<String>> sortedParameters = new TreeMap<>(parameters);
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
        sortedParameters.forEach(builder::queryParam);
        return builder.build().encode().toUriString().substring(1); // Remove '?' from the URI string
    }
}
