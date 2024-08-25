package com.bart.scorebetlive442.repository;

import com.bart.scorebetlive442.entity.ScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository extends JpaRepository<ScoreEntity, Long> {
}
