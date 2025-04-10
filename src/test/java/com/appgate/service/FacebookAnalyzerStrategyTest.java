package com.appgate.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.appgate.model.SocialMention;
import com.appgate.repository.SocialMentionRepository;
import com.appgate.service.strategy.FacebookAnalyzerStrategy;
import com.appgate.util.RiskLevel;

class FacebookAnalyzerStrategyTest {

	@Test
	void shouldReturnLowRiskForPositiveMessage() {
		SocialMentionRepository repository = mock(SocialMentionRepository.class);
		FacebookAnalyzerStrategy strategy = new FacebookAnalyzerStrategy(repository);

		SocialMention mention = new SocialMention();
		mention.setFacebookAccount("fb_account");
		mention.setMessage("Promo offer");
		mention.setFacebookComments(List.of("Good deal!"));

		RiskLevel result = strategy.analyze(mention);

		assertEquals(RiskLevel.LOW_RISK, result);
		verify(repository).saveFacebookPost(anyDouble(), anyString(), eq("fb_account"));
	}
}