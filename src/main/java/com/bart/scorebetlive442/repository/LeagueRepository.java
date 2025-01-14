package com.bart.scorebetlive442.repository;

import com.bart.scorebetlive442.entity.LeagueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeagueRepository extends JpaRepository<LeagueEntity, Long>, DetachRepository<LeagueEntity> {

    @Query("SELECT l FROM LeagueEntity l JOIN FETCH l.teams t WHERE l.id = :id")
    Optional<LeagueEntity> getByIdEager(Long id);

    @Query("SELECT DISTINCT l FROM LeagueEntity l LEFT JOIN FETCH l.teams")
    List<LeagueEntity> findAllEager();

    List<LeagueEntity> getLeagueEntityByNameIsNot(String name);

    default List<LeagueEntity> findAllLive() {
        return getLeagueEntityByNameIsNot("DELETED");
    }

}
