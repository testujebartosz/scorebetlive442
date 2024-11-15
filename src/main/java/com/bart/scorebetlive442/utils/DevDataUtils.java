package com.bart.scorebetlive442.utils;

import com.bart.scorebetlive442.entity.LeagueEntity;
import com.bart.scorebetlive442.entity.TeamEntity;
import com.bart.scorebetlive442.repository.LeagueRepository;
import com.bart.scorebetlive442.repository.TeamRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
@Slf4j
public class DevDataUtils {
    private final LeagueRepository leagueRepository;
    private final TeamRepository teamRepository;

    public DevDataUtils(LeagueRepository leagueRepository, TeamRepository teamRepository) {
        this.leagueRepository = leagueRepository;
        this.teamRepository = teamRepository;
    }

    @PostConstruct
    public void init() {
        var leagueEntity = new LeagueEntity();
        leagueEntity.setName("Premier League");
        leagueEntity.setCountry("England");
        leagueEntity = leagueRepository.save(leagueEntity);
        log.debug("Created League: {}", leagueEntity);

        var leagueEntity2 = new LeagueEntity();
        leagueEntity2.setName("Ekstraklasa");
        leagueEntity2.setCountry("Poland");
        leagueEntity2 = leagueRepository.save(leagueEntity2);
        log.debug("Created League: {}", leagueEntity2);

        var leagueEntity3 = new LeagueEntity();
        leagueEntity3.setName("Primera Division");
        leagueEntity3.setCountry("Spain");
        leagueEntity3 = leagueRepository.save(leagueEntity3);
        log.debug("Created League: {}", leagueEntity3);

        var teamEntity = teamRepository.save(addTeam("Man Utd", "England", "Manchster", "1942-02-09", leagueEntity));
        log.debug("Created Team: {}", teamEntity);

        teamEntity = teamRepository.save(addTeam("Arsenal", "England", "London", "1999-02-09", leagueEntity));
        log.debug("Created Team: {}", teamEntity);

        teamEntity = teamRepository.save(addTeam("FKS Stal Mielec", "Poland", "Mielec", "1939-04-10", null));
        log.debug("Created Team: {}", teamEntity);


    }

    private TeamEntity addTeam(String name, String country, String city, String year, LeagueEntity leagueEntity) {
        var teamEntity = new TeamEntity();
        teamEntity.setName(name);
        teamEntity.setCountry(country);
        teamEntity.setCity(city);
        teamEntity.setFoundedYear(year);
        teamEntity.setLeagueEntity(leagueEntity);
        return teamEntity;
    }
}
