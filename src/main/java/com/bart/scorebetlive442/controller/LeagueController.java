package com.bart.scorebetlive442.controller;

import com.bart.scorebetlive442.mapper.LeagueMapper;
import com.bart.scorebetlive442.model.League;
import com.bart.scorebetlive442.model.json.AddTeamToLeagueJson;
import com.bart.scorebetlive442.model.json.LeagueJson;
import com.bart.scorebetlive442.model.json.LeagueMode;
import com.bart.scorebetlive442.model.json.ModifyTeamsInLeagueAction;
import com.bart.scorebetlive442.service.LeagueService;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
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
    public ResponseEntity<LeagueJson> createLeague(@RequestBody @JsonView(LeagueJson.View.CreateRequest.class)
                                                   LeagueJson leagueJson) {
        League createdLeague = leagueService.createLeague(leagueMapper.convertJsonToLeague(leagueJson));
        return new ResponseEntity<>(leagueMapper.convertLeagueToJson(createdLeague), HttpStatus.CREATED);
    }

    @GetMapping
    @JsonView(LeagueJson.View.GetResponse.class)
    public ResponseEntity<List<LeagueJson>> getLeagues(@RequestParam Optional<String> name,
                                                       @RequestParam Optional<String> country) {
        return new ResponseEntity<>(leagueMapper.convertLeagueToJson(leagueService.getLeagueBy(name,
                country)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @JsonView(LeagueJson.View.GetResponse.class)
    public ResponseEntity<LeagueJson> getLeagueById(@PathVariable Long id) {
        League league = leagueService.getLeagueById(id);
        LeagueJson leagueJson = leagueMapper.convertLeagueToJson(league);
        return new ResponseEntity<>(leagueJson, HttpStatus.OK);
    }

    @GetMapping("/short/{id}")
    @JsonView(LeagueJson.View.GetResponseShort.class)
    public ResponseEntity<LeagueJson> getLeagueByIdShort(@PathVariable Long id) {
        League league = leagueService.getLeagueByIdShort(id);
        LeagueJson leagueJson = leagueMapper.convertLeagueToJson(league);
        return new ResponseEntity<>(leagueJson, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<MappingJacksonValue> getAllLeagues(@RequestParam LeagueMode mode) {
        try {
            var leagueModels = leagueService.getAllLeaguesByMode(mode);
            var leagueJsons = leagueMapper.convertLeagueToJson(leagueModels);
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(leagueJsons);
            mappingJacksonValue.setSerializationView(switch (mode) {
                case OFF -> LeagueJson.View.GetResponseShort.class;
                case ALL -> LeagueJson.View.GetResponse.class;
                case COUNT -> LeagueJson.View.GetResponseCountTeams.class;
            });
            return ResponseEntity.ok(mappingJacksonValue);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PatchMapping(value = "/{id}")
    @JsonView(LeagueJson.View.GetResponse.class)
    public ResponseEntity<LeagueJson> updateLeague(@PathVariable("id") Long id, @RequestBody @JsonView(LeagueJson.View.CreateRequest.class)
    LeagueJson leagueJson) {
        League updatedLeague = leagueService.updateLeague(id, leagueMapper.convertJsonToLeague(leagueJson));
        return new ResponseEntity<>(leagueMapper.convertLeagueToJson(updatedLeague), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteLeague(@PathVariable Long id) {
        boolean isLeagueDeleted = leagueService.deleteLeagueById(id);
        if (isLeagueDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/addTeam")
    public ResponseEntity<?> addTeamToLeague(@RequestParam Long leagueId,
                                             @RequestBody @Valid AddTeamToLeagueJson addTeamToLeagueJson) {
        leagueService.addTeamToLeague(leagueId, addTeamToLeagueJson.teams());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/deleteTeam")
    public ResponseEntity<Void> deleteTeamFromLeague(@RequestParam Long leagueId,
                                                     @RequestBody @Valid AddTeamToLeagueJson teamIds) {
        leagueService.deleteTeamFromLeague(leagueId, teamIds.teams());
        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "/modifyTeams")
    public ResponseEntity<?> modifyTeamsInLeague(@RequestParam Long leagueId,
                                                 @RequestParam ModifyTeamsInLeagueAction action,
                                                 @RequestBody @Valid AddTeamToLeagueJson addTeamToLeagueJson) {
        return switch (action) {
            case ADD -> addTeamToLeague(leagueId, addTeamToLeagueJson);
            case REMOVE -> deleteTeamFromLeague(leagueId, addTeamToLeagueJson);
        };
    }
}
