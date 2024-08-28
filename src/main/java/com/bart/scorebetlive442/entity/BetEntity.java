package com.bart.scorebetlive442.entity;

import com.bart.scorebetlive442.model.Team;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "bet")
public class BetEntity {

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

    private double odds1;
    private double odds2;
    private double oddsX;
}
