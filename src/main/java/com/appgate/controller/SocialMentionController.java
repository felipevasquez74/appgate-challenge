package com.appgate.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/social-mention")
public class SocialMentionController {

	@PostMapping("/analyze")
	public ResponseEntity<?> analyze() {
		return ResponseEntity.ok("ok");
	}
}