package com.paysonix.signaturevalidator.api.endpoint;

import com.paysonix.signaturevalidator.api.dto.SignatureResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

@Api(value = "Signature API")
public interface SignatureController {

    @ApiOperation(value = "Generate a signature based on input parameters", response = SignatureResponseDto.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Token", value = "Authentication token required", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "parameters", value = "Dynamic parameters whose names and values are not predefined", required = true, dataType = "string", paramType = "query", allowMultiple = true)

    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully generated signature"),
            @ApiResponse(code = 400, message = "Parameters should not be empty"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    ResponseEntity<SignatureResponseDto> generateSignature(
            String operationId,
            MultiValueMap<String, String> parameters);
}
