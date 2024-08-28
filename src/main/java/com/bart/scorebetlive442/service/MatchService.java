package com.bart.scorebetlive442.service;

import com.bart.scorebetlive442.entity.MatchEntity;
import com.bart.scorebetlive442.entity.TeamEntity;
import com.bart.scorebetlive442.mapper.MatchMapper;
import com.bart.scorebetlive442.mapper.TeamMapper;
import com.bart.scorebetlive442.model.Match;
import com.bart.scorebetlive442.repository.MatchRepository;
import com.bart.scorebetlive442.repository.TeamRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchService {

    private final MatchRepository matchRepository;
    private final MatchMapper matchMapper;
    private final TeamMapper teamMapper;

    public MatchService(MatchRepository matchRepository, MatchMapper matchMapper, TeamMapper teamMapper) {
        this.matchRepository = matchRepository;
        this.matchMapper = matchMapper;
        this.teamMapper = teamMapper;

    }

    public Match createMatch(Match match) {
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
        if(matchRepository.existsById(id)) {
            matchRepository.deleteById(id);
            return true;
        } else
            return false;
    }

    public Match updateMatch(Long id, Match match) {
        MatchEntity matchById = matchRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Match with id " + id + "not found"));

        matchMapper.updateMatchFromDto(match, matchById);

        MatchEntity updatedMatchEntity = matchRepository.save(matchById);

        return matchMapper.toMatchModel(updatedMatchEntity);
    }
}
