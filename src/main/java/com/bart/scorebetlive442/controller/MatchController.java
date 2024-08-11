package com.bart.scorebetlive442.controller;

import com.bart.scorebetlive442.mapper.MatchMapper;
import com.bart.scorebetlive442.model.Match;
import com.bart.scorebetlive442.model.json.MatchCreateJson;
import com.bart.scorebetlive442.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/matches")
public class MatchController {

    private final MatchService matchService;
    private final MatchMapper matchMapper;

    @Autowired
    public MatchController(MatchService matchService, MatchMapper matchMapper) {
        this.matchService = matchService;
        this.matchMapper = matchMapper;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Match> createTeam(@RequestBody MatchCreateJson matchCreateJson) {
        Match match = matchMapper.convertJsonToMatch(matchCreateJson);
        Match createdMatch = matchService.createMatch(match);
        return new ResponseEntity<>(createdMatch, HttpStatus.CREATED);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Match> getTeam(@PathVariable(name = "id") Long matchId) {
        Match match = matchService.getMatch(matchId);
        if (match != null) {
            return new ResponseEntity<>(match, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Match>> getAllMatches() {
        List<Match> allMatches = matchService.getAllMatches();

        if (allMatches.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(allMatches, HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/remove/{id}")
    public ResponseEntity<Match> deleteTeam(@PathVariable Long id) {
        boolean isMatchRemoved = matchService.removeMatchById(id);
        if (isMatchRemoved) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
