package com.bart.scorebetlive442.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "Match")
public class MatchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "team_home_id", nullable = false)
    private TeamEntity teamHome;

    @ManyToOne
    @JoinColumn(name = "team_away_id", nullable = false)
    private TeamEntity teamAway;

    private Date dateTime;
    private String stadiumName;
    private String city;
    private int scoreHome;
    private int scoreAway;

}
