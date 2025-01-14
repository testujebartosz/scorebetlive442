package com.bart.scorebetlive442.service;

import com.bart.scorebetlive442.config.BetAppProperties;
import com.bart.scorebetlive442.entity.TeamEntity;
import com.bart.scorebetlive442.mapper.TeamMapper;
import com.bart.scorebetlive442.model.Team;
import com.bart.scorebetlive442.repository.TeamRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Validator;
import jakarta.validation.groups.Default;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;
    private final Validator validator;
    private final Integer matchLength;

    public TeamService(TeamRepository teamRepository,
                       TeamMapper teamMapper,
                       @Value("${bet-app.matchLength}") Integer abc,
                       BetAppProperties betAppProperties,
                       Validator validator) {
        this.teamRepository = teamRepository;
        this.teamMapper = teamMapper;
        this.validator = validator;
        this.matchLength = abc;
        var now = LocalDateTime.now();
        var until = now.plusMinutes(matchLength);
        System.out.println(abc + " " + abc.getClass());
        System.out.println(now + " " + until);
    }

    public Team createTeam(Team team) {
        var validate = validator.validate(team, Team.Group.Create.class, Default.class);
        if (!validate.isEmpty()) {
            System.out.println(validate);
            throw new RuntimeException("Validation failed");
        }

        TeamEntity toSave = teamMapper.toTeamEntity(team);
        TeamEntity saved = teamRepository.save(toSave);

        return teamMapper.toTeamModel(saved);
    }

    public Team getTeamById(Long id) {
       return teamRepository.findById(id)
               .map(teamMapper::toTeamModel)
               .orElse(null);
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

        var validate = validator.validate(team, Team.Group.Update.class, Default.class);
        if (!validate.isEmpty()) {
            System.out.println(validate);
            throw new RuntimeException("Validation failed");
        }

        teamMapper.updateTeamFromDto(team, teamById);
        TeamEntity updatedTeamEntity = teamRepository.save(teamById);

        return teamMapper.toTeamModel(updatedTeamEntity);
    }

    public List<Team> getTeamByExample(Team exampleTeam) {
//        Example.of(teamMapper.toTeamEntity(exampleTeam), ExampleMatcher.matching();
        var teamEntityExample = Example.of(teamMapper.toTeamEntity(exampleTeam), ExampleMatcher.matching().withIgnoreCase("city"));
        System.out.println(teamEntityExample);

        return teamRepository.findAll(teamEntityExample).stream().map(teamMapper::toTeamModel).collect(Collectors.toList());
    }
}
