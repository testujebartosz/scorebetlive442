package com.bart.scorebetlive442.controller;

import com.bart.scorebetlive442.mapper.MatchMapper;
import com.bart.scorebetlive442.model.Match;
import com.bart.scorebetlive442.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/match")
public class MatchController {

    private final MatchService matchService;
    private final MatchMapper matchMapper;

    @Autowired
    public MatchController(MatchService matchService, MatchMapper matchMapper) {
        this.matchService = matchService;
        this.matchMapper = matchMapper;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<MatchResponseJson> createTeam(@RequestBody MatchCreateJson matchCreateJson) {

        Match match = matchMapper.convertJsonToMatch(matchCreateJson);
        Match createdMatch = matchService.createMatch(match);
        MatchResponseJson matchResponse = matchMapper.convertMatchToJson(createdMatch);
        return new ResponseEntity<>(matchResponse, HttpStatus.CREATED);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<MatchResponseJson> getTeam(@PathVariable(name = "id") Long matchId) {
        Match match = matchService.getMatch(matchId);

        if (match != null) {
            MatchResponseJson matchResponse = matchMapper.convertMatchToJson(match);
            return new ResponseEntity<>(matchResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<MatchResponseJson>> getAllMatches() {
        List<Match> allMatches = matchService.getAllMatches();

        if (allMatches.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            List<MatchResponseJson> matchResponse = allMatches.stream()
                    .map(matchMapper::convertMatchToJson)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(matchResponse, HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/remove/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        boolean isMatchRemoved = matchService.removeMatchById(id);
        if (isMatchRemoved) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping(value = "/update/{id}")
    public ResponseEntity<MatchResponseJson> updateTeam(@PathVariable Long id, @RequestBody MatchCreateJson bodyMatch) {
        Match match = matchMapper.convertJsonToMatch(bodyMatch);
        Match updatedMatch = matchService.updateMatch(id, match);
        return new ResponseEntity<>(matchMapper.convertMatchToJson(updatedMatch), HttpStatus.OK);
    }
}
