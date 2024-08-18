package com.bart.scorebetlive442.repository;

import com.bart.scorebetlive442.entity.BetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BetRepository extends JpaRepository<BetEntity, Long> {
}
