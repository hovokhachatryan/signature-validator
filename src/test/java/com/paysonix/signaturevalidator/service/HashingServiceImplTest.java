package com.paysonix.signaturevalidator.service;

import com.paysonix.signaturevalidator.TestDataUtils;
import com.paysonix.signaturevalidator.config.SecurityProperties;
import com.paysonix.signaturevalidator.service.impl.HashingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HashingServiceImplTest {

    private HashingServiceImpl hashingServiceImpl;

    @BeforeEach
    public void setUp() {
        SecurityProperties securityProperties = new SecurityProperties(TestDataUtils.TEST_TOKEN_KEY, TestDataUtils.TEST_HMAC_ALGO);
        hashingServiceImpl = new HashingServiceImpl(securityProperties); // Assuming this does not require any dependencies
    }

    @Test
    public void generateSignatureTest() {
        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.put("bKey", List.of("bValue"));
        data.put("aKey", List.of("aValue"));

        String expectedHex = "315a01ae241584ba557818665f03c8c2b8dba14a696eda60b2bc98aa45235f80"; // This value would be the expected output
        String actualHex = hashingServiceImpl.generateSignature(data);
        assertEquals(expectedHex, actualHex);
    }

    @Test
    public void generateSignatureSortingTest() {
        MultiValueMap<String, String> unsortedData = new LinkedMultiValueMap<>();
        unsortedData.put("bKey", List.of("bValue"));
        unsortedData.put("aKey", List.of("aValue"));

        MultiValueMap<String, String> sortedData = new LinkedMultiValueMap<>();
        sortedData.put("aKey", List.of("aValue"));
        sortedData.put("bKey", List.of("bValue"));

        String hexFromUnsortedData = hashingServiceImpl.generateSignature(unsortedData);
        String hexFromSortedData = hashingServiceImpl.generateSignature(sortedData);

        assertEquals(hexFromSortedData, hexFromUnsortedData);
    }

}
