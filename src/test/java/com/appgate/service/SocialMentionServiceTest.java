package com.appgate.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.appgate.exception.NoSocialAccountException;
import com.appgate.model.SocialMention;
import com.appgate.service.strategy.SocialMediaAnalyzerStrategy;
import com.appgate.util.RiskLevel;
class SocialMentionServiceTest {

    @Test
    void shouldAnalyzeWithFacebookStrategy() {
        SocialMediaAnalyzerStrategy strategy = mock(SocialMediaAnalyzerStrategy.class);
        when(strategy.supports(any())).thenReturn(true);
        when(strategy.analyze(any())).thenReturn(RiskLevel.LOW_RISK);

        SocialMentionService service = new SocialMentionService(List.of(strategy));

        SocialMention mention = new SocialMention();
        mention.setFacebookAccount("fb_account");

        String result = service.analyze(mention);

        assertEquals("LOW_RISK", result);
        verify(strategy).analyze(any());
    }

    @Test
    void shouldThrowExceptionWhenNoSocialAccountProvided() {
        SocialMediaAnalyzerStrategy strategy = mock(SocialMediaAnalyzerStrategy.class);
        when(strategy.supports(any())).thenReturn(false);

        SocialMentionService service = new SocialMentionService(List.of(strategy));

        SocialMention mention = new SocialMention();

        assertThrows(NoSocialAccountException.class, () -> service.analyze(mention));
    }
}