package com.bart.scorebetlive442.service;

import com.bart.scorebetlive442.entity.LeagueEntity;
import com.bart.scorebetlive442.entity.TeamEntity;
import com.bart.scorebetlive442.mapper.LeagueMapper;
import com.bart.scorebetlive442.model.League;
import com.bart.scorebetlive442.repository.LeagueRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LeaugeService {

    private final LeagueRepository leagueRepository;
    private final LeagueMapper leagueMapper;
    private final TeamService teamService;

    public LeaugeService(LeagueRepository leagueRepository, LeagueMapper leagueMapper, TeamService teamService) {
        this.leagueRepository = leagueRepository;
        this.leagueMapper = leagueMapper;
        this.teamService = teamService;
    }

    @Transactional
    public League createLeague(League league) {

        Set<TeamEntity> teams = league.getTeams()
                .stream()
                .map(team -> teamService.findTeamEntityById(team.getId()))
                .collect(Collectors.toSet());

        LeagueEntity leagueEntity = leagueMapper.toLeagueEntity(league);
        leagueEntity.setTeams(teams);

        LeagueEntity saved = leagueRepository.save(leagueEntity);
        return leagueMapper.toLeagueModel(saved);
    }
}
