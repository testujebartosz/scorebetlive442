package com.bart.scorebetlive442.controller;

import com.bart.scorebetlive442.mapper.MatchMapper;
import com.bart.scorebetlive442.model.Match;
import com.bart.scorebetlive442.model.json.LeagueJson;
import com.bart.scorebetlive442.model.json.MatchJson;
import com.bart.scorebetlive442.service.MatchService;
import com.fasterxml.jackson.annotation.JsonView;
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

    @PostMapping
    @JsonView(MatchJson.View.CreateResponse.class)
    public ResponseEntity<MatchJson> createTeam(@RequestBody @JsonView(MatchJson.View.CreateRequest.class)
                                                    MatchJson matchJson) {

        Match createdMatch = matchService.createMatch(matchMapper.convertJsonToMatch(matchJson));
        return new ResponseEntity<>(matchMapper.convertMatchToJson(createdMatch), HttpStatus.CREATED);
    }


    @GetMapping(value = "/{id}")
    @JsonView(MatchJson.View.GetResponse.class)
    public ResponseEntity<MatchJson> getTeam(@PathVariable(name = "id") Long matchId) {
        Match match = matchService.getMatch(matchId);

        if (match != null) {
            return new ResponseEntity<>(matchMapper.convertMatchToJson(match), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/all")
    @JsonView(LeagueJson.View.GetResponse.class)
    public ResponseEntity<List<MatchJson>> getAllMatches() {
        List<Match> allMatches = matchService.getAllMatches();

        if (allMatches.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            List<MatchJson> matchResponse = allMatches.stream()
                    .map(matchMapper::convertMatchToJson)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(matchResponse, HttpStatus.OK);
        }
    }

    @PatchMapping(value = "/{id}")
    @JsonView(LeagueJson.View.GetResponse.class)
    public ResponseEntity<MatchJson> updateTeam(@PathVariable Long id, @RequestBody @JsonView(MatchJson.View.CreateRequest.class)
    MatchJson matchJson) {
        Match updatedMatch = matchService.updateMatch(id, matchMapper.convertJsonToMatch(matchJson));
        return new ResponseEntity<>(matchMapper.convertMatchToJson(updatedMatch), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        boolean isMatchRemoved = matchService.removeMatchById(id);
        if (isMatchRemoved) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
