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
@Table(name = "match")
public class MatchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_home_id", nullable = false)
    private TeamEntity teamHome;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_away_id", nullable = false)
    private TeamEntity teamAway;

    @Column(nullable = false)
    private Date dateTime;

    @Column(nullable = false)
    private String stadiumName;

    @Column(nullable = false)
    private String city;
}
