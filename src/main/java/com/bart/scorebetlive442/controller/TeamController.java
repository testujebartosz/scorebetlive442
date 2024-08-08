package com.bart.scorebetlive442.controller;

import com.bart.scorebetlive442.model.Team;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/team")
public class TeamController {

    private final List<Team> teams = new ArrayList<>();

    @PostMapping(value = "/create")
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {
        teams.add(team);
        return new ResponseEntity<>(team, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Team> getTeam(@PathVariable(name = "id") int teamId) {
        Team team = null;
        for (Team t : teams) {
            if (t.getId() == teamId) {
                team = t;
                break;
            }
        }

        if (team != null) {
            return new ResponseEntity<>(team, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Team>> getAllTeams(
            @RequestHeader(value = "X-User-Agent") String userAgent,
            @RequestParam(value = "country", required = false) String country)

    {

        List<Team> teamsByCountry = new ArrayList<>();
        if (country != null && !country.isEmpty()) {
            for (Team team : teams) {
                if (team.getCountry().equalsIgnoreCase(country)) {
                    teamsByCountry.add(team);
                }
            }
        } else {
            teamsByCountry = teams;
        }

        return new ResponseEntity<>(teamsByCountry, HttpStatus.OK);
    }

    @DeleteMapping(value = "/remove/{id}")
    public ResponseEntity<Team> deleteTeam(@PathVariable int id) {
        boolean teamToBeRemoved = teams.removeIf(team -> team.getId() == id);
        if (teamToBeRemoved) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
