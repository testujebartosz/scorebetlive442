package com.bart.scorebetlive442.model.json;

public enum LeagueMode {

    OFF,
    COUNT,
    ALL;

    public static LeagueMode fromValue(String value) {
        try {
            return LeagueMode.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            return OFF;
        }
    }
}
