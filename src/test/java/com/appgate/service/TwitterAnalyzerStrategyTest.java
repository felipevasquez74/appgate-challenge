package com.appgate.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import com.appgate.model.SocialMention;
import com.appgate.repository.SocialMentionRepository;
import com.appgate.repository.entity.SocialMentionRecord;
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

		ArgumentCaptor<SocialMentionRecord> captor = ArgumentCaptor.forClass(SocialMentionRecord.class);
		verify(repository).save(captor.capture());

		SocialMentionRecord savedRecord = captor.getValue();

		assertEquals("Twitter", savedRecord.getNetwork());
		assertEquals("twitter_account", savedRecord.getAccount());
		assertEquals("https://twitter.com/status/123", savedRecord.getUrl());
	}
}