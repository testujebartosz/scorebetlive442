package com.bart.scorebetlive442.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class League {

    private Long id;
    private String name;
    private String country;
    private Set<Team> teams;
    private String level;
}
