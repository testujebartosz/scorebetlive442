package com.bart.scorebetlive442.controller;

import com.bart.scorebetlive442.mapper.TeamMapper;
import com.bart.scorebetlive442.model.Team;
import com.bart.scorebetlive442.model.json.TeamCreateJson;
import com.bart.scorebetlive442.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public ResponseEntity<Team> createTeam(@RequestBody TeamCreateJson teamCreateJson) {
        Team team = teamMapper.convertJsonToTeam(teamCreateJson);
        Team createdTeam = teamService.createTeam(team);
        return new ResponseEntity<>(createdTeam, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Team> getTeam(@PathVariable(name = "id") Long teamId) {
        Team team = teamService.getTeamById(teamId);
        if (team != null) {
            return new ResponseEntity<>(team, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Team>> getAllTeams(
            @RequestHeader(value = "X-User-Agent") String userAgent,
            @RequestParam(value = "country", required = false) String country) {
        List<Team> allTeams = teamService.getAllTeams();
        List<Team> teamsByCountry = new ArrayList<>();
        if (country != null && !country.isEmpty()) {
            for (Team team : allTeams) {
                if (team.getCountry().equalsIgnoreCase(country)) {
                    teamsByCountry.add(team);
                }
            }
            return new ResponseEntity<>(teamsByCountry, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(allTeams, HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/remove/{id}")
    public ResponseEntity<Team> deleteTeam(@PathVariable Long id) {
        boolean isTeamRemoved = teamService.removeTeamById(id);
        if (isTeamRemoved) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
