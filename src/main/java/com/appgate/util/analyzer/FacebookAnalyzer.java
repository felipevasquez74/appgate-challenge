package com.appgate.util.analyzer;

public class FacebookAnalyzer {

	private FacebookAnalyzer() {
		// Private constructor to prevent instantiation
	}

	public static double calculateFacebookCommentsScore(String comments) {
		if (comments.contains("good") || comments.contains("positive")) {
			return 80.0;
		}
		return 40.0;
	}

	public static double analyzePost(String message, String account) {
		if (message.contains("sale") || message.contains("promo")) {
			return 70.0;
		}
		return 30.0;
	}
}
