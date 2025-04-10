package com.appgate.service.strategy;

import com.appgate.model.SocialMention;
import com.appgate.util.RiskLevel;

public interface SocialMediaAnalyzerStrategy {
	boolean supports(SocialMention mention);
	RiskLevel analyze(SocialMention mention);
}