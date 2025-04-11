package com.appgate.service.strategy;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.appgate.model.SocialMention;
import com.appgate.repository.SocialMentionRepository;
import com.appgate.repository.entity.SocialMentionRecord;
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
		save(tweetScore, fullMessage, mention);

		return RiskLevel.fromTwitterScore(tweetScore);
	}
	
	private void save(double tweetScore, String fullMessage, SocialMention mention) {
        repository.save(SocialMentionRecord.builder()
                .network("Twitter")
                .score(tweetScore)
                .message(fullMessage)
                .account(mention.getTweeterAccount())
                .url(mention.getTweeterUrl())
                .analyzedAt(LocalDateTime.now())
                .build());

		log.info("Twitter post saved successfully for account {}", mention.getTweeterAccount());
	}
}
