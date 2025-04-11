package com.appgate.repository.entity;


import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "social_mentions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SocialMentionRecord {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String network;
	private double score;
	private String message;
	private String account;
	private String url;
	private LocalDateTime analyzedAt;
}