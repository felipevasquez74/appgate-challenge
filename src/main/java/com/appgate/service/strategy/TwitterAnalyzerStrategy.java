package com.appgate.service.strategy;

import org.springframework.stereotype.Component;

import com.appgate.model.SocialMention;
import com.appgate.repository.SocialMentionRepository;
import com.appgate.util.RiskLevel;
import com.appgate.util.analyzer.TweeterAnalyzer;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TwitterAnalyzerStrategy implements SocialMediaAnalyzerStrategy {

	private final SocialMentionRepository repository;

	public TwitterAnalyzerStrategy(SocialMentionRepository repository) {
		this.repository = repository;
	}

	@Override
	public boolean supports(SocialMention mention) {
	    return mention.getTweeterAccount() != null && !mention.getTweeterAccount().isBlank();
	}

	@Override
	public RiskLevel analyze(SocialMention mention) {
		log.info("Starting Twitter analysis for account: {}", mention.getTweeterAccount());

		String fullMessage = "tweeterMessage: " + mention.getMessage();
		mention.setMessage(fullMessage);

		double tweetScore = TweeterAnalyzer.analyzeTweet(fullMessage, mention.getTweeterUrl(),
				mention.getTweeterAccount());
		log.debug("Twitter score: {}", tweetScore);

		repository.saveTweet(tweetScore, fullMessage, mention.getTweeterUrl(), mention.getTweeterAccount());
		log.info("Twitter post saved successfully");

		return RiskLevel.fromTwitterScore(tweetScore);
	}
}
