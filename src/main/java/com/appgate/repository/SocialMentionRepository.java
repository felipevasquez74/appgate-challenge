package com.appgate.repository;

import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class SocialMentionRepository {

	public void saveFacebookPost(double score, String message, String account) {
		log.info("Saving Facebook post: score={}, account={}, message={}", score, account, message);
	}

	public void saveTweet(double score, String message, String url, String account) {
		log.info("Saving Tweet: score={}, account={}, url={}, message={}", score, account, url, message);
	}
}