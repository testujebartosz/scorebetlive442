package com.bart.scorebetlive442.model.json;

import com.bart.scorebetlive442.model.Team;

public record BetResponseJson(
        Team teamA,
        Team teamB,
        double odds1,
        double odds2,
        double oddsX
) {
}
