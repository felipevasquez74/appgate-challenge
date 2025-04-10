package com.appgate.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appgate.dto.ApiResponse;
import com.appgate.model.SocialMention;
import com.appgate.service.SocialMentionService;
import com.appgate.util.MDCLoggingFilter;
import com.appgate.util.ResponseBuilder;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/social-mention")
@Slf4j
@Tag(name = "Social Mention Analyzer", description = "API para analizar menciones en redes sociales")
public class SocialMentionController {

	private final SocialMentionService service;

	public SocialMentionController(SocialMentionService service) {
		this.service = service;
	}

	@PostMapping("/analyze")
	@Operation(summary = "Analiza una mención social", description = "Determina si la mención social en Facebook o Twitter es de alto, medio o bajo riesgo")
	public ResponseEntity<ApiResponse<Map<String, String>>> analyze(@Valid @RequestBody SocialMention socialMention,
			HttpServletRequest request) {
		MDCLoggingFilter.builderMDC();
		log.info("Request received {} ", socialMention);
		String result = service.analyze(socialMention);
		Map<String, String> data = Map.of("riskLevel", result);
		return ResponseEntity.ok(
				ResponseBuilder.buildResponse(data, "Risk analysis completed successfully", HttpStatus.OK, request));
	}
}