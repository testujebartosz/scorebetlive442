package com.bart.scorebetlive442.service;

import com.bart.scorebetlive442.entity.LeagueEntity;
import com.bart.scorebetlive442.mapper.LeagueMapper;
import com.bart.scorebetlive442.model.League;
import com.bart.scorebetlive442.repository.LeagueRepository;
import jakarta.validation.Validator;
import jakarta.validation.groups.Default;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeagueService {

    private final LeagueRepository leagueRepository;
    private final LeagueMapper leagueMapper;
    private final Validator validator;

    public LeagueService(LeagueRepository leagueRepository, LeagueMapper leagueMapper, Validator validator) {
        this.leagueRepository = leagueRepository;
        this.leagueMapper = leagueMapper;
        this.validator = validator;
    }

//    @Transactional
    public League createLeague(League league) {

        var validate = validator.validate(league, League.Group.Create.class, Default.class);
        if (!validate.isEmpty()) {
            System.out.println(validate);
            throw new RuntimeException("xxxx");
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

    public List<League> getAllLeagues() {
        return leagueRepository.findAll().stream().map(leagueMapper::toLeagueModel).toList();
    }

    public List<League> getLeagueBy(Optional<String> name, Optional<String> country) {

        LeagueEntity leagueEntity = new LeagueEntity();
        name.ifPresent(leagueEntity::setName);
        country.ifPresent(leagueEntity::setCountry);

        var all = leagueRepository.findAll(Example.of(leagueEntity));
        return leagueMapper.toLeagueModel(all);

    }
}
