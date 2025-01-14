package com.bart.scorebetlive442.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "league")
@SQLDelete(sql = "UPDATE league SET name = 'DELETED' WHERE id = ?")
@SQLRestriction(value = "name <> 'DELETED'")
public class LeagueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String country;

    @OneToMany(mappedBy = "leagueEntity", orphanRemoval = true)
    private Set<TeamEntity> teams = new LinkedHashSet<>();
}
