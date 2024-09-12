package com.bart.scorebetlive442.model.json;

import jakarta.validation.constraints.NotNull;

public record TeamCreateJson(
        @NotNull String name,
        String city,
        String country,
        String foundedYear
) {
}
