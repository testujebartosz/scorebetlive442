package com.bart.scorebetlive442.model.json;

import jakarta.validation.constraints.NotEmpty;

import java.util.Set;

public record AddTeamToLeagueJson(
    @NotEmpty Set<Long> teams) {
}
