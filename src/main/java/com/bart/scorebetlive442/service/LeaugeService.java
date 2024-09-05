package com.bart.scorebetlive442.service;

import com.bart.scorebetlive442.mapper.LeagueMapper;
import com.bart.scorebetlive442.repository.LeagueRepository;
import org.springframework.stereotype.Service;

@Service
public class LeaugeService {

    private final LeagueRepository leagueRepository;
    private final LeagueMapper leagueMapper;


    public LeaugeService(LeagueRepository leagueRepository, LeagueMapper leagueMapper) {
        this.leagueRepository = leagueRepository;
        this.leagueMapper = leagueMapper;
    }
}
