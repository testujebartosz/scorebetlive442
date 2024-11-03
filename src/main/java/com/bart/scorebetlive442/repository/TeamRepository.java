package com.bart.scorebetlive442.repository;

import com.bart.scorebetlive442.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, Long> {
    @Query("SELECT t FROM TeamEntity t WHERE t.id IN :teamIds AND (t.leagueEntity IS NULL OR t.leagueEntity.id != :leagueId)")
    List<TeamEntity> findTeamsByIdsAndNotInLeague(@Param("teamIds") Set<Long> teamIds, @Param("leagueId") Long leagueId);

    @Query("SELECT t FROM TeamEntity t WHERE t.id IN :teamIds AND t.leagueEntity.id = :leagueId")
    List<TeamEntity> findTeamsByIdsAndInLeague(@Param("teamIds") Set<Long> teamIds, @Param("leagueId") Long leagueId);
}
