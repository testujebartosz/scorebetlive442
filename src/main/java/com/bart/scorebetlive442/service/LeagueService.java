package com.bart.scorebetlive442.service;

import com.bart.scorebetlive442.entity.LeagueEntity;
import com.bart.scorebetlive442.entity.TeamEntity;
import com.bart.scorebetlive442.mapper.LeagueMapper;
import com.bart.scorebetlive442.model.League;
import com.bart.scorebetlive442.repository.LeagueRepository;
import com.bart.scorebetlive442.repository.TeamRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Validator;
import jakarta.validation.groups.Default;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class LeagueService {

    private final LeagueRepository leagueRepository;
    private final LeagueMapper leagueMapper;
    private final Validator validator;
    private final TeamRepository teamRepository;
    private final EntityManager entityManager;
    private final JdbcTemplate jdbcTemplate;

    public LeagueService(LeagueRepository leagueRepository,
                         LeagueMapper leagueMapper,
                         Validator validator,
                         TeamRepository teamRepository,
                         EntityManager entityManager,
                         JdbcTemplate jdbcTemplate) {
        this.leagueRepository = leagueRepository;
        this.leagueMapper = leagueMapper;
        this.validator = validator;
        this.teamRepository = teamRepository;
        this.entityManager = entityManager;
        this.jdbcTemplate = jdbcTemplate;
    }

    //    @Transactional
    public League createLeague(League league) {
        var validate = validator.validate(league, League.Group.Create.class, Default.class);
        if (!validate.isEmpty()) {
            System.out.println(validate);
            throw new RuntimeException("Validation failed");
        }
//        Set<TeamEntity> teams = league.getTeams()
//                .stream()
//                .map(team -> teamService.findTeamEntityById(team.getId()))
//                .collect(Collectors.toSet());
        LeagueEntity leagueEntity = leagueMapper.toLeagueEntity(league);
//        leagueEntity.setTeams(teams);
        LeagueEntity saved = leagueRepository.save(leagueEntity);
        return leagueMapper.toLeagueModel(saved);
    }

    public League getLeagueById(Long id) {
        return leagueRepository.getByIdEager(id)
                .map(leagueMapper::toLeagueModel)
                .orElseThrow(() -> {
            log.error("No such league with ID: {}", id);
            return new RuntimeException("No such league");
        });
    }

    public League getLeagueByIdShort(Long id) {
        var byId = leagueRepository.findById(id).get();
        entityManager.detach(byId);
        return Optional.of(byId)
                .map(leagueMapper::toLeagueModel)
                .orElseThrow(() -> {
                    log.error("No such league with ID: {}", id);
                    return new RuntimeException("No such league");
                });
    }

    public List<League> getLeagueBy(Optional<String> name, Optional<String> country) {
        LeagueEntity leagueEntity = new LeagueEntity();
        name.ifPresent(leagueEntity::setName);
        country.ifPresent(leagueEntity::setCountry);
        var all = leagueRepository.findAll(Example.of(leagueEntity));
        return leagueMapper.toLeagueModel(all);
    }

    public boolean deleteLeagueById(Long id) {
        if (leagueRepository.existsById(id)) {
            leagueRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public League updateLeague(Long id, League league) {
        LeagueEntity existingLeagueEntity = leagueRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("League not found"));

        var validate = validator.validate(league, League.Group.Update.class, Default.class);
        if (!validate.isEmpty()) {
            System.out.println(validate);
            throw new RuntimeException("Validation failed");
        }

        leagueMapper.updateLeagueFromDto(league, existingLeagueEntity);
        LeagueEntity updatedLeagueEntity = leagueRepository.save(existingLeagueEntity);

        return leagueMapper.toLeagueModel(updatedLeagueEntity);
    }

    public void addTeamToLeague(Long leagueId, Set<Long> teamIds) {
        //sprawdzenie czy liga istnieje
        LeagueEntity existingLeague = leagueRepository.findById(leagueId)
                .orElseThrow(() -> {
                    log.error("No such league with ID: {}", leagueId);
                    return new RuntimeException("No such league");
                });

        // Pobranie drużyn po ich identyfikatorach, które nie są przypisane do tej ligi
        List<TeamEntity> foundTeamsById = teamRepository.findTeamsByIdsAndNotInLeague(teamIds, leagueId);

        // Sprawdzenie, czy wszystkie drużyny zostały znalezione
        if (foundTeamsById.size() != teamIds.size()) {
            Set<Long> foundTeamsIds = foundTeamsById
                    .stream()
                    .map(TeamEntity::getId)
                    .collect(Collectors.toSet());
            Set<Long> missingTeamIds = new HashSet<>(teamIds);
            missingTeamIds.removeAll(foundTeamsIds);

            log.error("Teams not found or already assigned: {}", missingTeamIds);
            throw new RuntimeException("Teams not found or already assigned: " + missingTeamIds);
        }

        // Przypisanie drużyn do ligi
        foundTeamsById.forEach(team -> team.setLeagueEntity(existingLeague));
        // Zapisanie drużyn
        teamRepository.saveAll(foundTeamsById);
    }

    public void deleteTeamFromLeague(Long leagueId, Set<Long> teamIds) {
        // Sprawdzenie, czy liga istnieje
        leagueRepository.findById(leagueId)
                .orElseThrow(() -> {
                    log.error("No such league with ID: {}", leagueId);
                    return new RuntimeException("No such league");
                });

        // Pobranie drużyn, które są przypisane do danej ligi na podstawie podanych identyfikatorów
        List<TeamEntity> teamsToRemove = teamRepository.findTeamsByIdsAndInLeague(teamIds, leagueId);

        // Sprawdzenie, czy żadne drużyny nie zostały znalezione
        if (teamsToRemove.size() != teamIds.size()) {
            Set<Long> foundTeamIds = teamsToRemove.stream()
                    .map(TeamEntity::getId)
                    .collect(Collectors.toSet());
            Set<Long> missingTeamIds = new HashSet<>(teamIds);
            missingTeamIds.removeAll(foundTeamIds);

            log.error("Not all teams found for removal: {}", missingTeamIds);
            throw new RuntimeException("Not all teams found for removal: " + missingTeamIds);
        }

        // Usunięcie przypisania drużyn do ligi
        teamsToRemove.forEach(team -> team.setLeagueEntity(null));
        teamRepository.saveAll(teamsToRemove);
    }
}
