package com.appgate.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.appgate.model.SocialMention;
import com.appgate.service.SocialMentionService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(SocialMentionController.class)
class SocialMentionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SocialMentionService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnRiskLevelResponse() throws Exception {
        when(service.analyze(any())).thenReturn("LOW_RISK");

        SocialMention mention = new SocialMention();
        mention.setFacebookAccount("fb_account");
        mention.setMessage("Test message");

        mockMvc.perform(post("/api/v1/social-mention/analyze")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mention)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.data.riskLevel").value("LOW_RISK"));
    }
}