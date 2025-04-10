package com.appgate.util;

public class TextNormalizer {

	public static String normalize(String text) {
		if (text == null) {
			return "";
		}
		return text.trim().toLowerCase();
	}
}