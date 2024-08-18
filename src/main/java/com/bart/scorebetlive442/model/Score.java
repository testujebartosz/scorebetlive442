package com.bart.scorebetlive442.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Score {

    private Long id;
    private Team teamHome;
    private Team teamAway;
    private int scoreHome;
    private int scoreAway;

}
