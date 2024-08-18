package com.bart.scorebetlive442.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bet {

    private Long id;
    private Team teamHome;
    private Team teamAway;
    private double odds1;
    private double odds2;
    private double oddsX;
}
