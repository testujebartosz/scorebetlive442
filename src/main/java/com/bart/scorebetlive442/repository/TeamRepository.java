package com.bart.scorebetlive442.repository;

import com.bart.scorebetlive442.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, Long> {
    TeamEntity findByCountryIgnoreCase(String country);
}
