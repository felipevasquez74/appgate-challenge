package com.appgate.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.appgate.exception.NoSocialAccountException;
import com.appgate.model.SocialMention;
import com.appgate.service.strategy.SocialMediaAnalyzerStrategy;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SocialMentionService {

    private final List<SocialMediaAnalyzerStrategy> strategies;

    public SocialMentionService(List<SocialMediaAnalyzerStrategy> strategies) {
        this.strategies = strategies;
    }

    public String analyze(SocialMention mention) {
        return strategies.stream()
                .filter(strategy -> strategy.supports(mention))
                .findFirst()
                .map(strategy -> {
                    return strategy.analyze(mention).name();
                })
                .orElseThrow(NoSocialAccountException::new);
    }
}