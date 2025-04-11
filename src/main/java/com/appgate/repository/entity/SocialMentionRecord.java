package com.appgate.repository.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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