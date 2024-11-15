package com.bart.scorebetlive442.repository;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DetachRepositoryImpl<T> implements DetachRepository<T> {

    private final EntityManager entityManager;

    @Autowired
    public DetachRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public void detach(T entity) {
        entityManager.detach(entity);
    }
}
