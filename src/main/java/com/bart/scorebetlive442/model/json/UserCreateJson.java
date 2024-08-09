package com.bart.scorebetlive442.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserCreateJson(
    @JsonProperty("login") String username,
    String password,
    String firstName,
    String lastName,
    String email,
    String dateOfBirth,
    String country
) {
}
