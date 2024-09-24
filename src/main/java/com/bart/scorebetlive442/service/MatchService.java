package com.bart.scorebetlive442.service;

import com.bart.scorebetlive442.entity.MatchEntity;
import com.bart.scorebetlive442.mapper.MatchMapper;
import com.bart.scorebetlive442.model.League;
import com.bart.scorebetlive442.model.Match;
import com.bart.scorebetlive442.repository.MatchRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Validator;
import jakarta.validation.groups.Default;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchService {

    private final MatchRepository matchRepository;
    private final MatchMapper matchMapper;
    private final Validator validator;

    public MatchService(MatchRepository matchRepository, MatchMapper matchMapper, Validator validator) {
        this.matchRepository = matchRepository;
        this.matchMapper = matchMapper;
        this.validator = validator;
    }

    public Match createMatch(Match match) {
        var validate = validator.validate(match, Match.Group.Create.class, Default.class);
        if (!validate.isEmpty()) {
            System.out.println(validate);
            throw new RuntimeException("Validation failed");
        }

        MatchEntity toSave = matchMapper.toMatchEntity(match);
        MatchEntity saved = matchRepository.save(toSave);

        return matchMapper.toMatchModel(saved);
    }


    public Match getMatch(Long id) {
        return matchRepository.findById(id)
                .map(matchMapper::toMatchModel)
                .orElse(null);
    }

    public List<Match> getAllMatches() {
        return matchRepository.findAll()
                .stream()
                .map(matchMapper::toMatchModel)
                .collect(Collectors.toList());

    }

    public boolean removeMatchById(Long id) {
        if (matchRepository.existsById(id)) {
            matchRepository.deleteById(id);
            return true;
        } else
            return false;
    }

    public Match updateMatch(Long id, Match match) {
        MatchEntity matchById = matchRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Match with id " + id + "not found"));

        var validate = validator.validate(match, Match.Group.Create.class, Default.class);
        if (!validate.isEmpty()) {
            System.out.println(validate);
            throw new RuntimeException("Validation failed");
        }

        matchMapper.updateMatchFromDto(match, matchById);
        MatchEntity updatedMatchEntity = matchRepository.save(matchById);

        return matchMapper.toMatchModel(updatedMatchEntity);
    }
}
