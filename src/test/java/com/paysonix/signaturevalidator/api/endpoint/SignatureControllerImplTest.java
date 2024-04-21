package com.paysonix.signaturevalidator.api.endpoint;

import com.paysonix.signaturevalidator.api.dto.SignatureResponseDto;
import com.paysonix.signaturevalidator.api.dto.SignatureResponseStatus;
import com.paysonix.signaturevalidator.api.endpoint.impl.SignatureControllerImpl;
import com.paysonix.signaturevalidator.service.impl.SignatureServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
public class SignatureControllerImplTest {

    private MockMvc mvc;

    @Mock
    private SignatureServiceImpl signatureServiceImpl;

    private SignatureControllerImpl signatureControllerImpl;

    @BeforeEach
    public void setUp() {
        signatureControllerImpl = new SignatureControllerImpl(signatureServiceImpl);
        mvc = standaloneSetup(signatureControllerImpl).build();
    }

    @Test
    public void generateSignature_Successful() throws Exception {
        SignatureResponseDto responseDto = new SignatureResponseDto(SignatureResponseStatus.SUCCESS, List.of(new SignatureResponseDto.Result("signature_value")));
        when(signatureServiceImpl.processRequest(any(), any())).thenReturn(responseDto);

        mvc.perform(post("/api/operation/testOperation")
                        .param("key", "value"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(SignatureResponseStatus.SUCCESS.name()))
                .andExpect(jsonPath("$.result[0].signature").value("signature_value"));
    }


}
