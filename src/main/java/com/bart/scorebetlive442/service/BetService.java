package com.bart.scorebetlive442.service;

import com.bart.scorebetlive442.mapper.BetMapper;
import com.bart.scorebetlive442.repository.BetRepository;
import org.springframework.stereotype.Service;

@Service
public class BetService {

    private final BetRepository betRepository;
    private final BetMapper betMapper;

    public BetService(BetRepository betRepository, BetMapper betMapper) {
        this.betRepository = betRepository;
        this.betMapper = betMapper;
    }
}
