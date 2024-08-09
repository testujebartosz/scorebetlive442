package com.bart.scorebetlive442.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String dateOfBirth;
    private String country;
    private int id;
}
