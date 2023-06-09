package com.avocat.controller.contract;

import com.avocat.common.AbstractMockMvcController;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ContractControllerTests extends AbstractMockMvcController {

    @Test
    void shouldCreateNewContractThenWillReturn201() throws Exception {

        String json = getJsonCompany();

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/v1/customer/d5d7da4a-4520-446e-9a6a-aaf4b76f803f/branch-office/65344a5e-81ce-4eb3-b16b-955d26b73ede/company/49fbb8cc-3c47-4177-8bd0-47062c22211c/contracts")
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                                .header("Authorization", "Bearer " + defaultAccessToken)
                                .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty());
    }

    @Test
    void shouldGetAllCompaniesThenWillReturnListOfContractsAndHttpStatus200() throws Exception {

        mockMvc.perform(get("/v1/customer/d5d7da4a-4520-446e-9a6a-aaf4b76f803f/contracts")
                        .header("Authorization", "Bearer " + defaultAccessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetContractByIdThenWillReturn200() throws Exception {

        mockMvc.perform(get("/v1/customer/d5d7da4a-4520-446e-9a6a-aaf4b76f803f/contracts/0735fe7e-182a-4d51-ab10-fbbdf9d289e8")
                        .header("Authorization", "Bearer " + defaultAccessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    private String getJsonCompany() {
        return """
                {                
                    "name":"%s",
                    "annotationBilling":"%s",
                    "generalNote":"teste",
                    "adjustmentDate":"",
                    "closingDate":""                                                 
                }
                """.formatted(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString());
    }

    private String getText() {
        return """
                teste
                """;
    }
}
