package com.bart.scorebetlive442.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "test")
public class TestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private String missing;

    @Basic(fetch = FetchType.EAGER)
    private String name;

    @Basic(fetch = FetchType.LAZY)
    private String lazyValue;

}
