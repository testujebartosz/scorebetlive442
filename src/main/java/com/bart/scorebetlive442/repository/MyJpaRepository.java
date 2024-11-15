package com.bart.scorebetlive442.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MyJpaRepository<T, ID> extends JpaRepository<T, ID>, DetachRepository<T> {
}
