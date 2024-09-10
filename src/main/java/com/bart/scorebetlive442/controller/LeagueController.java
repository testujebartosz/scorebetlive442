package com.bart.scorebetlive442.controller;

import com.bart.scorebetlive442.mapper.LeagueMapper;
import com.bart.scorebetlive442.model.League;
import com.bart.scorebetlive442.model.Team;
import com.bart.scorebetlive442.model.json.LeagueCreateJson;
import com.bart.scorebetlive442.model.json.LeagueResponseJson;
import com.bart.scorebetlive442.service.LeaugeService;
import com.bart.scorebetlive442.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/league")
public class LeagueController {

    private final LeaugeService leaugeService;
    private final LeagueMapper leagueMapper;
    private final TeamService teamService;

    @Autowired
    public LeagueController(LeaugeService leaugeService, LeagueMapper leagueMapper, TeamService teamService) {
        this.leaugeService = leaugeService;
        this.leagueMapper = leagueMapper;
        this.teamService = teamService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<LeagueResponseJson> createLeague(@RequestBody LeagueCreateJson leagueCreateJson) {

        League league = leagueMapper.convertJsonToLeague(leagueCreateJson);

        Set<Team> teams = leagueCreateJson.teamIds()
                .stream()
                .map(teamService::getTeamById)
                .collect(Collectors.toSet());

        league.setTeams(teams);

        League createdLeague = leaugeService.createLeague(league);
        LeagueResponseJson leagueResponse = leagueMapper.convertLeagueToJson(createdLeague);

        return new ResponseEntity<>(leagueResponse, HttpStatus.CREATED);
    }
}
