package com.appgate.util.analyzer;

public class TweeterAnalyzer {
	
	private TweeterAnalyzer() {
		// Private constructor to prevent instantiation
	}

	public static double analyzeTweet(String message, String url, String account) {
		if (message.contains("alert") || message.contains("warning")) {
			return -0.8;
		}
		return 0.9;
	}
}