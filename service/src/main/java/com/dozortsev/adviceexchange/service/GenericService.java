package com.dozortsev.adviceexchange.service;

import com.dozortsev.adviceexchange.domain.AbstractEntity;

import java.io.Serializable;
import java.util.Set;

public interface GenericService<ID extends Serializable, T extends AbstractEntity<ID>> {

    ID create(T entity);

    void delete(T entity);

    void deleteById(ID id);

    T findById(ID id);

    Set<T> findByUserId(ID userId);

    T update(T entity);
}
