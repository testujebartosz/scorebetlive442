package com.bart.scorebetlive442.service;

import com.bart.scorebetlive442.entity.TeamEntity;
import com.bart.scorebetlive442.mapper.TeamMapper;
import com.bart.scorebetlive442.model.Team;
import com.bart.scorebetlive442.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    private final List<Team> teams = new ArrayList<>();
    private final TeamMapper teamMapper;
    private Long currentTeamId = 1L;

    public TeamService(TeamRepository teamRepository, TeamMapper teamMapper) {
        this.teamRepository = teamRepository;
        this.teamMapper = teamMapper;
    }

    public Team createTeam(Team team) {
        TeamEntity toSave = teamMapper.toEntity(team);
        TeamEntity saved = teamRepository.save(toSave);


        return teamMapper.toModel(saved);
    }

    public Team getTeamById(Long id) {
        for (Team team : teams) {
            if (team.getId().equals(id)) {
                return team;
            }
        }
        return null;
    }

    public List<Team> getAllTeams() {
        return new ArrayList<>(teams);
    }

    public boolean removeTeamById(Long id) {
        return teams.removeIf(team -> Objects.equals(team.getId(), id));
    }
}
