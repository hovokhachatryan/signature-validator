package com.paysonix.signaturevalidator.service.impl;

import com.paysonix.signaturevalidator.api.dto.SignatureResponseDto;
import com.paysonix.signaturevalidator.api.dto.SignatureResponseStatus;
import com.paysonix.signaturevalidator.service.HashingService;
import com.paysonix.signaturevalidator.service.SignatureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;

@Service
@RequiredArgsConstructor
@Slf4j
public class SignatureServiceImpl implements SignatureService {

    private final HashingService hashingService;

    @Override
    public SignatureResponseDto processRequest(String operationId, MultiValueMap<String, String> parameters) {
        Instant start = Instant.now();

        String signature = hashingService.generateSignature(parameters);
        SignatureResponseDto.Result result = new SignatureResponseDto.Result(signature);

        Instant end = Instant.now();
        log.info(String.format("Signature generation finished for operation %s, durationMs=%s",
                operationId, Duration.between(start, end).toMillis()));
        return new SignatureResponseDto(SignatureResponseStatus.SUCCESS, Collections.singletonList(result));
    }

}