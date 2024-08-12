package com.bart.scorebetlive442.model.json;

public record UserResponseJson(
        String username,
        String password,
        String firstName,
        String lastName,
        String email,
        String dateOfBirth,
        String country,
        Long id
) {
}
