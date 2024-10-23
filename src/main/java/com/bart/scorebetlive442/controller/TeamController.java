package com.bart.scorebetlive442.controller;

import com.bart.scorebetlive442.mapper.TeamMapper;
import com.bart.scorebetlive442.model.Team;
import com.bart.scorebetlive442.model.json.TeamJson;
import com.bart.scorebetlive442.service.TeamService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/team")
public class TeamController {

    private final TeamService teamService;
    private final TeamMapper teamMapper;

    @Autowired
    public TeamController(TeamService teamService, TeamMapper teamMapper) {
        this.teamService = teamService;
        this.teamMapper = teamMapper;
    }

    @PostMapping
    @JsonView(TeamJson.View.CreateResponse.class)
    public ResponseEntity<TeamJson> createTeam(@RequestBody @JsonView(TeamJson.View.CreateRequest.class)
                                               TeamJson teamJson) {
        Team createdTeam = teamService.createTeam(teamMapper.convertJsonToTeam(teamJson));
        return new ResponseEntity<>(teamMapper.convertTeamToJson(createdTeam), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    @JsonView(TeamJson.View.GetResponse.class)
    public ResponseEntity<TeamJson> getTeam(@PathVariable(name = "id") Long teamId) {
        Team team = teamService.getTeamById(teamId);
        if (team != null) {
            return new ResponseEntity<>(teamMapper.convertTeamToJson(team), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/all")
    @JsonView(TeamJson.View.GetResponse.class)
    public ResponseEntity<List<TeamJson>> getAllTeams(
            @RequestHeader(value = "X-User-Agent") String userAgent,
            @RequestParam(value = "country", required = false) String country) {

        List<Team> allTeams = teamService.getAllTeams();
        List<TeamJson> teamResponse = allTeams.stream()
                .filter(team -> country == null || country.isEmpty() || team.getCountry().equalsIgnoreCase(country))
                .map(teamMapper::convertTeamToJson)
                .collect(Collectors.toList());

        return new ResponseEntity<>(teamResponse, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        boolean isTeamRemoved = teamService.removeTeamById(id);
        if (isTeamRemoved) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping(value = "{id}")
    @JsonView(TeamJson.View.GetResponse.class)
    public ResponseEntity<TeamJson> updateTeam(@PathVariable Long id, @RequestBody @JsonView(TeamJson.View.CreateRequest.class)
    TeamJson teamJson) {
        Team updatedTeam = teamService.updateTeamById(id, teamMapper.convertJsonToTeam(teamJson));
        return new ResponseEntity<>(teamMapper.convertTeamToJson(updatedTeam), HttpStatus.OK);
    }
}
