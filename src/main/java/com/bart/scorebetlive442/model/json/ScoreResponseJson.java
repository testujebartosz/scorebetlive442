package com.bart.scorebetlive442.model.json;

import com.bart.scorebetlive442.model.Team;

public record ScoreResponseJson(
        Team teamA,
        Team teamB,
        int scoreA,
        int scoreB
) {
}
