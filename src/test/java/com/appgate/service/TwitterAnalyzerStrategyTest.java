package com.appgate.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

import com.appgate.model.SocialMention;
import com.appgate.repository.SocialMentionRepository;
import com.appgate.service.strategy.TwitterAnalyzerStrategy;
import com.appgate.util.RiskLevel;

class TwitterAnalyzerStrategyTest {

	@Test
	void shouldReturnLowRiskForNeutralMessage() {
		SocialMentionRepository repository = mock(SocialMentionRepository.class);
		TwitterAnalyzerStrategy strategy = new TwitterAnalyzerStrategy(repository);

		SocialMention mention = new SocialMention();
		mention.setTweeterAccount("twitter_account");
		mention.setMessage("Just an update");
		mention.setTweeterUrl("https://twitter.com/status/123");

		RiskLevel result = strategy.analyze(mention);

		assertEquals(RiskLevel.LOW_RISK, result);
		verify(repository).saveTweet(anyDouble(), anyString(), eq("https://twitter.com/status/123"),
				eq("twitter_account"));
	}
}