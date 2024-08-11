package com.bart.scorebetlive442.service;

import com.bart.scorebetlive442.model.Match;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatchService {

    private final List<Match> matchesList = new ArrayList<>();
    private Long currentMatchId = 1L;

    public Match createMatch(Match match) {
        match.setId(currentMatchId++);
        matchesList.add(match);
        return match;
    }

    public Match getMatch(Long id) {
        for (Match match : matchesList) {
            if (match.getId().equals(id)) {
                return match;
            }
        }
        return null;
    }

    public List<Match> getAllMatches() {
        return matchesList;
    }

    public boolean removeMatchById(Long id) {
        return matchesList.removeIf(match -> match.getId().equals(id));
    }
}
