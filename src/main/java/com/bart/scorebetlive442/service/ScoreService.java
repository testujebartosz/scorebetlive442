package com.bart.scorebetlive442.service;

import com.bart.scorebetlive442.mapper.ScoreMapper;
import com.bart.scorebetlive442.repository.ScoreRepository;
import org.springframework.stereotype.Service;

@Service
public class ScoreService {

    private final ScoreRepository scoreRepository;
    private final ScoreMapper scoreMapper;

    public ScoreService(ScoreRepository scoreRepository, ScoreMapper scoreMapper) {
        this.scoreRepository = scoreRepository;
        this.scoreMapper = scoreMapper;
    }
}
