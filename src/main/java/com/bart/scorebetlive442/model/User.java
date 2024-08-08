package com.bart.scorebetlive442.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
