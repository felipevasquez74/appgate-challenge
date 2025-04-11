package com.appgate.service.strategy;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

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

		String combinedMessage = buildCombinedMessage(mention);
		mention.setMessage(combinedMessage);

		String normalizedMessage = TextNormalizer.normalize(combinedMessage);

		double commentsScore = FacebookAnalyzer.calculateFacebookCommentsScore(normalizedMessage);
		log.debug("Calculated Facebook comments score: {}", commentsScore);

		double postScore = determinePostScore(commentsScore, normalizedMessage, mention.getFacebookAccount());
		log.debug("Final Facebook post score: {}", postScore);

		if (postScore > -100) {
			repository.saveFacebookPost(postScore, normalizedMessage, mention.getFacebookAccount());
			log.info("Facebook post saved successfully for account: {}", mention.getFacebookAccount());
		}

		return RiskLevel.fromFacebookScore(postScore);
	}

	private String buildCombinedMessage(SocialMention mention) {
		String baseMessage = "facebookMessage: " + mention.getMessage();

		String comments = Optional.ofNullable(mention.getFacebookComments()).orElse(Collections.emptyList()).stream()
				.collect(Collectors.joining(" "));

		return baseMessage + " || comments: " + comments;
	}

	private double determinePostScore(double commentsScore, String normalizedMessage, String account) {
		if (commentsScore < 50) {
			return Double.sum(commentsScore, -100);
		}
		return FacebookAnalyzer.analyzePost(normalizedMessage, account);
	}
}