package com.bart.scorebetlive442.model.json;

import com.bart.scorebetlive442.model.Team;

import java.util.Date;

public record MatchCreateJson(
        Long id,
        Team teamHome,
        Team teamAway,
        Date dateTime,
        String stadiumName,
        String city,
        Integer scoreHome,
        Integer scoreAway
) {
}
