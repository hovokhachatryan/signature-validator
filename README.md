# Signature Validator API

## Overview
REST API for generating digital signatures based on provided parameters.

### Example API Call
Generate a signature using URL:

POST "http://localhost:8080/api/operation/{operationId}?key1=value1&key2=value2" 

Header "Token: {Use token from application.properties}"

### Test Coverage

Authentication logic validation through **TokenAuthenticationInterceptorTest**.

API response through **SignatureControllerTest**.

Core service logic verification through **SignatureServiceTest** and **HashingServiceTest**.

Exception handling through **GlobalExceptionHandlerTest**.
