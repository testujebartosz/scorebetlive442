package com.bart.scorebetlive442.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Match {

    private Long id;
    private Team teamHome;
    private Team teamAway;
    private Date dateTime;
    private String stadiumName;
    private String city;
    private int scoreHome;
    private int scoreAway;
}
