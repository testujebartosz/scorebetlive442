package com.bart.scorebetlive442.model.json;

import com.bart.scorebetlive442.model.Team;

import java.util.Date;

public record MatchResponseJson(
        Long id,
        Team teamHome,
        Team teamAway,
        Date dateTime,
        String stadiumName,
        String city,
        int scoreHome,
        int scoreAway
) {
}
