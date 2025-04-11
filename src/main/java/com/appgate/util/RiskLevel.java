package com.appgate.util;

public enum RiskLevel {
	HIGH_RISK, MEDIUM_RISK, LOW_RISK;

	public static RiskLevel fromFacebookScore(double score) {
		if (score == -100d)
			return HIGH_RISK;
		else if (score > -100d && score < 50d)
			return MEDIUM_RISK;
		else if (score >= 50d)
			return LOW_RISK;

		throw new IllegalArgumentException("Invalid score: " + score);
	}

	public static RiskLevel fromTwitterScore(double score) {
		if (score >= -1 && score <= -0.5)
			return HIGH_RISK;
		else if (score > -0.5 && score < 0.7)
			return MEDIUM_RISK;
		else
			return LOW_RISK;
	}
}