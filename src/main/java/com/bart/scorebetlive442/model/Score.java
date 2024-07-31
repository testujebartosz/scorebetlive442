package com.bart.scorebetlive442.model;

import lombok.Getter;

@Getter
public class Score {

    private String teamA;
    private String teamB;
    private int scoreA;
    private int scoreB;

    public Score(String teamA, int scoreA, int scoreB, String teamB) {
        this.teamA = teamA;
        this.scoreA = scoreA;
        this.teamB = teamB;
        this.scoreB = scoreB;
    }
}
