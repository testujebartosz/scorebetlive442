package com.bart.scorebetlive442.model.json;

import java.util.Set;

public record LeagueCreateJson(
        String name,
        String country,
        Set<Long> teamIds,
        String level
) {
}
