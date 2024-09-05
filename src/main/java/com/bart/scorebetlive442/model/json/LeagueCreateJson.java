package com.bart.scorebetlive442.model.json;

import com.bart.scorebetlive442.model.Team;

import java.util.Set;

public record LeagueCreateJson(
        String name,
        String country,
        Set<Team> teams,
        String level
) {
}
