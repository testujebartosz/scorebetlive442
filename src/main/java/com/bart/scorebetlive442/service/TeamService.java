package com.bart.scorebetlive442.service;

import com.bart.scorebetlive442.model.Team;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TeamService {

    private final List<Team> teams = new ArrayList<>();
    private Long currentTeamId = 1L;

    public Team createTeam(Team team) {
        team.setId(currentTeamId++);
        teams.add(team);
        return team;
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
