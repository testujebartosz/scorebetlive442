package com.bart.scorebetlive442.repository;

import com.bart.scorebetlive442.entity.LeagueEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeagueRepository extends JpaRepository<LeagueEntity, Long>, DetachRepository<LeagueEntity> {
//public interface LeagueRepository extends MyJpaRepository<LeagueEntity, Long> {

    @Query("SELECT l FROM LeagueEntity l JOIN FETCH l.teams t WHERE l.id = :id")
    Optional<LeagueEntity> getByIdEager(Long id);

    @Query("SELECT DISTINCT l FROM LeagueEntity l LEFT JOIN FETCH l.teams")
    List<LeagueEntity> findAllEager();

    @Transactional
    default List<LeagueEntity> findAllWithoutTeams() {
        var all = findAll();
        all.forEach(this::detach);
        return all;
    }

//    @Transactional(readOnly = true)
//    default List<LeagueEntity> findAllEager() {
//        var all = findAll();
//        all.forEach(x -> x.getTeams().size());
//        return all;
//    }

}
