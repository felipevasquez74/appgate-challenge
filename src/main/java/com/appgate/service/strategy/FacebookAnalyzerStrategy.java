package com.appgate.service.strategy;

import java.util.List;

import org.springframework.stereotype.Component;

import com.appgate.model.SocialMention;
import com.appgate.repository.SocialMentionRepository;
import com.appgate.util.RiskLevel;
import com.appgate.util.TextNormalizer;
import com.appgate.util.analyzer.FacebookAnalyzer;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FacebookAnalyzerStrategy implements SocialMediaAnalyzerStrategy {

	private final SocialMentionRepository repository;

	public FacebookAnalyzerStrategy(SocialMentionRepository repository) {
		this.repository = repository;
	}

	@Override
	public boolean supports(SocialMention mention) {
		return mention.getFacebookAccount() != null && !mention.getFacebookAccount().isBlank();
	}

	@Override
	public RiskLevel analyze(SocialMention mention) {
		log.info("Starting Facebook analysis for account: {}", mention.getFacebookAccount());

		String comments = String.join(" ",
				mention.getFacebookComments() != null ? mention.getFacebookComments() : List.of());
		String fullMessage = "facebookMessage: " + mention.getMessage() + " || comments: " + comments;

		double commentsScore = FacebookAnalyzer.calculateFacebookCommentsScore(fullMessage);
		log.debug("Facebook comments score: {}", commentsScore);

		double postScore = commentsScore < 50 ? -100
				: FacebookAnalyzer.analyzePost(fullMessage, mention.getFacebookAccount());
		log.debug("Facebook post score: {}", postScore);

		repository.saveFacebookPost(postScore, fullMessage, mention.getFacebookAccount());
		log.info("Facebook post saved successfully");

		return RiskLevel.fromFacebookScore(postScore);
	}
}