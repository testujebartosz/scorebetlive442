package com.bart.scorebetlive442.repository;

import com.bart.scorebetlive442.entity.LeagueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public interface LeagueRepository extends JpaRepository<LeagueEntity, Long> {

    @Query("SELECT l FROM LeagueEntity l JOIN FETCH l.teams t WHERE l.id = :id")
    Optional<LeagueEntity> getByIdEager(Long id);
    List<LeagueEntity> findAllByCountry(String country);

    default List<LeagueEntity> findAllFromPoland() {
        return findAllByCountry("Poland");
    }

    @Modifying
    void deleteAllByCountry(String country);

    @Modifying
    @Query("update LeagueEntity l set l.country = upper(l.country)")
    void correctAllCountries();

    default List<Long> findIdsByCountry(String country) {
        return findAllByCountry(country).stream().map(LeagueEntity::getId).collect(Collectors.toList());
    }

    @Query("select count(l) from LeagueEntity l where l.name = :name")
    long countByName(@Param("name") String name);
}
