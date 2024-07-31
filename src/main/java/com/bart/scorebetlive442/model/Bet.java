package com.bart.scorebetlive442.model;

import lombok.Getter;

@Getter
public class Bet {

    private String teamA;
    private String teamB;
    private double odds1;
    private double odds2;
    private double oddsX;

    public Bet(String teamA, String teamB, double odds1, double oddsX, double odds2) {
        this.teamA = teamA;
        this.oddsX = oddsX;
        this.odds1 = odds1;
        this.teamB = teamB;
        this.odds2 = odds2;
    }
}
