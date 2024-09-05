package com.bart.scorebetlive442.controller;

import com.bart.scorebetlive442.mapper.LeagueMapper;
import com.bart.scorebetlive442.service.LeaugeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/leagues")
public class LeagueController {

    private final LeaugeService leaugeService;
    private final LeagueMapper leagueMapper;

    @Autowired
    public LeagueController(LeaugeService leaugeService, LeagueMapper leagueMapper) {
        this.leaugeService = leaugeService;
        this.leagueMapper = leagueMapper;
    }
}
