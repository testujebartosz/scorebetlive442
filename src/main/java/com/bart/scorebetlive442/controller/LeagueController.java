package com.bart.scorebetlive442.controller;

import com.bart.scorebetlive442.mapper.LeagueMapper;
import com.bart.scorebetlive442.model.League;
import com.bart.scorebetlive442.model.json.LeagueJson;
import com.bart.scorebetlive442.service.LeagueService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/league")
public class LeagueController {

    private final LeagueService leagueService;
    private final LeagueMapper leagueMapper;

    @Autowired
    public LeagueController(LeagueService leagueService, LeagueMapper leagueMapper) {
        this.leagueService = leagueService;
        this.leagueMapper = leagueMapper;
    }

    @PostMapping
    @JsonView(LeagueJson.View.CreateResponse.class)
    public ResponseEntity<LeagueJson> createLeague(@RequestBody @JsonView(LeagueJson.View.CreateRequest.class) LeagueJson leagueJson) {
        League createdLeague = leagueService.createLeague(leagueMapper.convertJsonToLeague(leagueJson));
        return new ResponseEntity<>(leagueMapper.convertLeagueToJson(createdLeague), HttpStatus.CREATED);
    }

//    @GetMapping
//    @JsonView(LeagueJson.View.GetResponse.class)
//    public ResponseEntity<List<LeagueJson>> getAllLeagues() {
//        return new ResponseEntity<>(leagueMapper.convertLeagueToJson(leaugeService.getAllLeagues()), HttpStatus.OK);
//    }

    @GetMapping
    @JsonView(LeagueJson.View.GetResponse.class)
    public ResponseEntity<List<LeagueJson>> getLeagues(@RequestParam Optional<String> name,
                                                       @RequestParam Optional<String> country) {
        return new ResponseEntity<>(leagueMapper.convertLeagueToJson(leagueService.getLeagueBy(name, country)), HttpStatus.OK);
    }

    // usuwanie
    // update
}
