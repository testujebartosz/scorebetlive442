package com.bart.scorebetlive442.controller;

import com.bart.scorebetlive442.mapper.TeamMapper;
import com.bart.scorebetlive442.model.Team;
import com.bart.scorebetlive442.model.json.TeamCreateJson;
import com.bart.scorebetlive442.model.json.TeamResponseJson;
import com.bart.scorebetlive442.service.TeamService;
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

    @PostMapping(value = "/create")
    public ResponseEntity<TeamResponseJson> createTeam(@RequestBody TeamCreateJson teamCreateJson) {
        Team team = teamMapper.convertJsonToTeam(teamCreateJson);
        Team createdTeam = teamService.createTeam(team);
        TeamResponseJson teamResponse = teamMapper.convertTeamToJson(createdTeam);
        return new ResponseEntity<>(teamResponse, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TeamResponseJson> getTeam(@PathVariable(name = "id") Long teamId) {
        Team team = teamService.getTeamById(teamId);
        if (team != null) {
            TeamResponseJson teamResponse = teamMapper.convertTeamToJson(team);
            return new ResponseEntity<>(teamResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<TeamResponseJson>> getAllTeams(
            @RequestHeader(value = "X-User-Agent") String userAgent,
            @RequestParam(value = "country", required = false) String country) {

        List<Team> allTeams = teamService.getAllTeams();
        List<TeamResponseJson> teamResponse = allTeams.stream()
                .filter(team -> country == null || country.isEmpty() || team.getCountry().equalsIgnoreCase(country))
                .map(teamMapper::convertTeamToJson)
                .collect(Collectors.toList());

        return new ResponseEntity<>(teamResponse, HttpStatus.OK);
    }

    @DeleteMapping(value = "/remove/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        boolean isTeamRemoved = teamService.removeTeamById(id);
        if (isTeamRemoved) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
