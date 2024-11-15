package com.bart.scorebetlive442.repository;

public interface DetachRepository<T> {
    void detach(T entity);
}
