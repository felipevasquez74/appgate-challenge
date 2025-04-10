package com.appgate.model;

import java.util.List;

import lombok.Data;

@Data
public class SocialMention {
	private String message;
	private String facebookAccount;
	private String tweeterAccount;
	private String creationDate;
	private String tweeterUrl;
	private List<String> facebookComments;
}