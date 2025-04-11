package com.appgate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appgate.repository.entity.SocialMentionRecord;

@Repository
public interface SocialMentionRepository extends JpaRepository<SocialMentionRecord, Long> {
}