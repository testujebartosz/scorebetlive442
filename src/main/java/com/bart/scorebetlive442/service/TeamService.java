package com.bart.scorebetlive442.service;

import com.bart.scorebetlive442.entity.TeamEntity;
import com.bart.scorebetlive442.mapper.TeamMapper;
import com.bart.scorebetlive442.model.Team;
import com.bart.scorebetlive442.repository.TeamRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;
    public TeamService(TeamRepository teamRepository, TeamMapper teamMapper) {
        this.teamRepository = teamRepository;
        this.teamMapper = teamMapper;
    }

    public Team createTeam(Team team) {
        TeamEntity toSave = teamMapper.toEntity(team);
        TeamEntity saved = teamRepository.save(toSave);

        return teamMapper.toTeamModel(saved);
    }

    public Team getTeamById(Long id) {
       return teamRepository.findById(id)
               .map(teamMapper::toTeamModel)
               .orElse(null);
    }

    public TeamEntity findTeamEntityById(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Team not found with ID: " + id));
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll()
                .stream()
                .map(teamMapper::toTeamModel)
                .collect(Collectors.toList());
    }

    public boolean removeTeamById(Long id) {
        if (teamRepository.existsById(id)) {
            teamRepository.deleteById(id);
            return true;
        } else
            return false;
    }

    public Team updateTeamById(Long id, Team team) {
        TeamEntity teamById = teamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Team with id" + id + "not found"));

        if(team.getCity() != null) teamById.setCity(team.getCity());
        if(team.getName() != null) teamById.setName(team.getName());
        if(team.getCountry() != null) teamById.setCountry(team.getCountry());
        if(team.getFoundedYear() != null) teamById.setFoundedYear(team.getFoundedYear());

        teamRepository.save(teamById);

        return teamMapper.toTeamModel(teamById);
    }
}
