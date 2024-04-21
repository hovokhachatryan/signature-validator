package com.paysonix.signaturevalidator.service;

import com.paysonix.signaturevalidator.TestDataUtils;
import com.paysonix.signaturevalidator.api.dto.SignatureResponseDto;
import com.paysonix.signaturevalidator.api.dto.SignatureResponseStatus;
import com.paysonix.signaturevalidator.config.SecurityProperties;
import com.paysonix.signaturevalidator.service.impl.HashingServiceImpl;
import com.paysonix.signaturevalidator.service.impl.SignatureServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SignatureServiceImplTest {

    private SignatureServiceImpl signatureServiceImpl;

    @BeforeEach
    public void setUp() {
        SecurityProperties securityProperties = new SecurityProperties(TestDataUtils.TEST_TOKEN_KEY, TestDataUtils.TEST_HMAC_ALGO);
        HashingServiceImpl hashingServiceImpl = new HashingServiceImpl(securityProperties);
        signatureServiceImpl = new SignatureServiceImpl(hashingServiceImpl);
    }

    @Test
    public void generateSignatureTest() {
        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.put("bKey", List.of("bValue"));
        data.put("aKey", List.of("aValue"));

        String expectedHex = "315a01ae241584ba557818665f03c8c2b8dba14a696eda60b2bc98aa45235f80";
        SignatureResponseDto signatureResponseDto = signatureServiceImpl.processRequest("", data);
        assertEquals(expectedHex, signatureResponseDto.getResult().get(0).getSignature());
        assertEquals(SignatureResponseStatus.SUCCESS, signatureResponseDto.getStatus());
    }

}
