package com.bart.scorebetlive442.model.json;

public record TeamCreateJson(
        String name,
        String city,
        String country,
        Long id,
        int foundedYear
) {
}
