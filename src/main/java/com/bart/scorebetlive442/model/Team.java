package com.bart.scorebetlive442.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Team {

    private String name;
    private String city;
    private String country;
    private int id;
    private int foundedYear;
}
